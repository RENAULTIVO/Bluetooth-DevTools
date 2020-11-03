package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;

public class ServerDialog extends Dialog {

    Activity activity;

    public ServerDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        create();
        show();
    }

}