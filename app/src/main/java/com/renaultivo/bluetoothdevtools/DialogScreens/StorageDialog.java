package com.renaultivo.bluetoothdevtools.DialogScreens;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.renaultivo.bluetoothdevtools.R;

public class StorageDialog extends DefaultDialogScreen {

    Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public StorageDialog(Activity activity) {
        super(activity);
        this.activity = activity;

        setContentView(R.layout.storage_dialog);

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