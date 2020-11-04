package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.BuildConfig;
import com.renaultivo.bluetoothdevtools.R;

public class DevicesListDialog extends Dialog {

    Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public DevicesListDialog(Activity activity) {
        super(activity);
        this.activity = activity;

        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(R.layout.devices_dialog);

        create();
        show();
    }

}
