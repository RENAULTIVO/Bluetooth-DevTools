package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class DevicesListDialog extends Dialog {

    Activity activity;

    public DevicesListDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        create();
        show();
    }

}
