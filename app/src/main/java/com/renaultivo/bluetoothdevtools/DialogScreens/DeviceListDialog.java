package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.R;
import com.renaultivo.bluetoothdevtools.elements.DeviceElement;

import java.util.Set;

public class DeviceListDialog extends DefaultDialogScreen {

    Activity activity;
    BluetoothAdapter bluetoothAdapter = null;

    LinearLayout deviceListParent;
    LinearLayout bluetoothAdvice;
    LinearLayout devicesList;

    Button bluetoothButton;
    ImageButton closeButton;

    Set<BluetoothDevice> getDevicesList() {
        return bluetoothAdapter.getBondedDevices();
    }

    void mountDeviceList(Set<BluetoothDevice> devices) {

        if (bluetoothAdvice.getParent() != null) {
            hideBluetoothAdvice();
        }

        devicesList.removeAllViews();

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
        deviceListParent.addView(bluetoothAdvice);
        bluetoothAdvice.setVisibility(View.VISIBLE);
    }

    void hideBluetoothAdvice() {
        bluetoothAdvice.setVisibility(View.INVISIBLE);
        deviceListParent.removeView(bluetoothAdvice);
    }

    void requestBluetoothEnable() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivity(intent);
    }

    void getUsedElements() {
        closeButton = findViewById(R.id.closeButton);
        deviceListParent = findViewById(R.id.deviceListParent);
        bluetoothAdvice = findViewById(R.id.turnOnBluetoothBanner);
        devicesList = findViewById(R.id.devicesList);
        bluetoothButton = findViewById(R.id.enableBluetoothButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public DeviceListDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        setContentView(R.layout.devices_dialog);

        masterContainer = findViewById(R.id.masterContainer);

        getUsedElements();
        setBluetoothEventReceivers();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestBluetoothEnable();
            }
        });

        if (bluetoothAdapter.isEnabled()) {
            mountDeviceList(getDevicesList());
        }

        create();
        show();
        open();

    }

    void setBluetoothEventReceivers() {

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
