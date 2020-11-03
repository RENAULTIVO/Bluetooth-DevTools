package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;

public class StorageDialog extends Dialog {

    Activity activity;

    public StorageDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        create();
        show();
    }

}