package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.BuildConfig;
import com.renaultivo.bluetoothdevtools.R;
import com.renaultivo.bluetoothdevtools.elements.DeviceElement;

import java.util.Set;

public class DeviceListDialog extends DefaultDialogScreen {

    Activity activity;
    BluetoothAdapter bluetoothAdapter = null;

    LinearLayout deviceListParent;
    LinearLayout turnOnBluetoothBanner;
    LinearLayout devicesList;

    void getDevicesList() {

        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();

        devicesList.removeAllViews();

        if (turnOnBluetoothBanner.getParent() != null) {
            turnOnBluetoothBanner.setVisibility(View.INVISIBLE);
            deviceListParent.removeView(turnOnBluetoothBanner);
        }

        int fadeInTime = 150;

        for (BluetoothDevice bluetoothDevice : devices) {

            DeviceElement deviceElement = new DeviceElement(activity, bluetoothDevice, fadeInTime);

            deviceElement.setLeftButtonText("Info");
            deviceElement.setRightButtonText("Connect");

            devicesList.addView(deviceElement);

            fadeInTime += 150;

        }

    }

    void showBluetoothAdvice() {
        devicesList.removeAllViews();
        deviceListParent.addView(turnOnBluetoothBanner);
        turnOnBluetoothBanner.setVisibility(View.VISIBLE);
    }

    void requestBluetoothEnable() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public DeviceListDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        setContentView(R.layout.devices_dialog);

        this.masterContainer = findViewById(R.id.masterContainer);
        ImageButton closeButton = findViewById(R.id.closeButton);
        deviceListParent = findViewById(R.id.deviceListParent);
        turnOnBluetoothBanner = findViewById(R.id.turnOnBluetoothBanner);
        devicesList = findViewById(R.id.devicesList);
        Button enableBluetoothButton = findViewById(R.id.enableBluetoothButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

        enableBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestBluetoothEnable();
            }
        });

        setReceivers();

        if (bluetoothAdapter.isEnabled()) {
            getDevicesList();
        }

        create();
        show();
        open();
    }

    void setReceivers() {

        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {

                    case BluetoothAdapter.STATE_ON:
                        getDevicesList();
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        showBluetoothAdvice();
                        break;

                }

            }
        }, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

    }


}
