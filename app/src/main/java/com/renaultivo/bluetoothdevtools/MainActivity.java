package com.renaultivo.bluetoothdevtools;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.DialogScreens.DeviceListDialog;
import com.renaultivo.bluetoothdevtools.DialogScreens.ServerDialog;
import com.renaultivo.bluetoothdevtools.DialogScreens.SettingsDialog;
import com.renaultivo.bluetoothdevtools.DialogScreens.StorageDialog;

public class MainActivity extends Activity {

    Activity activity;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        activity = this;
        setContentView(R.layout.main_activity);

        LinearLayout devicesListButton = findViewById(R.id.devicesListButton);
        LinearLayout serverButton = findViewById(R.id.serverButton);
        LinearLayout settingsButton = findViewById(R.id.settingsButton);
        LinearLayout storageButton = findViewById(R.id.storageButton);

        devicesListButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                new DeviceListDialog(activity);
            }
        });

        serverButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                new ServerDialog(activity);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                new SettingsDialog(activity);
            }
        });

        storageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                new StorageDialog(activity);
            }
        });

    }

}
