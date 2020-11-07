package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.BuildConfig;
import com.renaultivo.bluetoothdevtools.R;

public class SettingsDialog extends DefaultDialogScreen {

    Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public SettingsDialog(Activity activity) {
        super(activity);
        this.activity = activity;

        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(R.layout.settings_dialog);

        masterContainer = findViewById(R.id.masterContainer);

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });

        create();
        show();
        open();
    }

}