package com.renaultivo.bluetoothdevtools.elements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.renaultivo.bluetoothdevtools.R;

public class DefaultButton extends androidx.appcompat.widget.AppCompatButton {

    LinearLayout.LayoutParams layoutParams;

    public void setMargins(int vertical, int horizontal) {
        layoutParams.setMargins(horizontal, vertical, horizontal, vertical);
        setLayoutParams(layoutParams);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public DefaultButton(Context context) {
        super(context);

        int scale = (int) context.getResources().getDisplayMetrics().density;

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setPadding(20 * scale, 10 * scale,20 * scale,10 * scale);
        setTextColor(Color.parseColor("#DDDDDD"));
        setLayoutParams(layoutParams);
        setBackgroundDrawable(context.getDrawable(R.drawable.default_button));

    }

}
