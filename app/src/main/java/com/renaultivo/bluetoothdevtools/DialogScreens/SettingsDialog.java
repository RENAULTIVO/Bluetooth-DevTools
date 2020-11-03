package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;

public class SettingsDialog extends Dialog {

    Activity activity;

    public SettingsDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        create();
        show();
    }

}