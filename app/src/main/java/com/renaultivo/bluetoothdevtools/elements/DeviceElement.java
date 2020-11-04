package com.renaultivo.bluetoothdevtools.elements;

import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.renaultivo.bluetoothdevtools.R;

public class DeviceElement extends LinearLayout {

    Activity activity;
    BluetoothDevice bluetoothDevice;

    ImageView deviceIcon;
    LinearLayout elementHeader;
    LinearLayout deviceInfo;
    LinearLayout deviceOptions;

    TextView deviceName;
    TextView deviceMAC;

    Button leftButton;
    Button rightButton;

    int scale;

    void createBaseElements() {

        deviceIcon = new ImageView(activity);

        elementHeader = new LinearLayout(activity);
        deviceInfo = new LinearLayout(activity);
        deviceOptions = new LinearLayout(activity);

        deviceName = new TextView(activity);
        deviceMAC = new TextView(activity);

        leftButton = new Button(activity);
        rightButton = new Button(activity);

        deviceInfo.addView(deviceName);
        deviceInfo.addView(deviceMAC);

        elementHeader.addView(deviceIcon);
        elementHeader.addView(deviceInfo);

        deviceOptions.addView(leftButton);
        deviceOptions.addView(rightButton);

        addView(elementHeader);
        addView(deviceOptions);

    }

    void setDeviceElementStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,20,20,20);
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.VERTICAL);
        setBackground(activity.getDrawable(R.drawable.device_element));
        setLayoutParams(layoutParams);
        setPadding(30, 20, 30, 20);
    }

    void setHeaderStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,20,20,20);
        elementHeader.setGravity(Gravity.CENTER_VERTICAL);
        elementHeader.setOrientation(LinearLayout.HORIZONTAL);
        elementHeader.setBackground(activity.getDrawable(R.drawable.device_element_header));
        elementHeader.setLayoutParams(layoutParams);
    }

    void setInfoStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) (scale * 10),20,20,20);
        deviceInfo.setGravity(Gravity.LEFT);
        deviceInfo.setOrientation(LinearLayout.VERTICAL);
        deviceInfo.setLayoutParams(layoutParams);
    }

    void setOptionsStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        deviceOptions.setGravity(Gravity.CENTER);
        deviceOptions.setOrientation(LinearLayout.HORIZONTAL);
        deviceOptions.setLayoutParams(layoutParams);
    }

    void setIconStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (scale * 50), (int) (scale * 50));
        layoutParams.setMargins(0, (int)(scale * 5), 0, (int)(scale * 5));
        deviceIcon.setLayoutParams(layoutParams);
    }

    void setButtonsStyle() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,10,10,10);

        leftButton.setTextColor(Color.parseColor("#DDDDDD"));
        leftButton.setBackground(activity.getDrawable(R.drawable.default_button_light));
        leftButton.setLayoutParams(layoutParams);

        rightButton.setTextColor(Color.parseColor("#DDDDDD"));
        rightButton.setBackground(activity.getDrawable(R.drawable.default_button_light));
        rightButton.setLayoutParams(layoutParams);
    }

    void setBaseElementsLayoutParams() {

        setDeviceElementStyle();
        setHeaderStyle();
        setInfoStyle();
        setOptionsStyle();
        setIconStyle();
        setButtonsStyle();

    }

    void setBaseElementsTextStyle() {
        deviceName.setTextColor(Color.parseColor("#DDDDDD"));
        deviceMAC.setTextColor(Color.parseColor("#DDDDDD"));
    }

    void setStyles() {
        setBaseElementsLayoutParams();
        setBaseElementsTextStyle();
        setVisibility(View.INVISIBLE);
    }

    public void setIcon() {

        switch (bluetoothDevice.getBluetoothClass().getMajorDeviceClass()) {

            case BluetoothClass.Device.Major.COMPUTER:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_computer));
                break;
            case BluetoothClass.Device.Major.AUDIO_VIDEO:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_headset));
                break;
            case BluetoothClass.Device.Major.IMAGING:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_imaging));
                break;
            case BluetoothClass.Device.Major.MISC:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_misc));
                break;
            case BluetoothClass.Device.Major.NETWORKING:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_networking));
                break;
            case BluetoothClass.Device.Major.PERIPHERAL:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_peripheral));
                break;
            case BluetoothClass.Device.Major.HEALTH:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_health));
                break;
            case BluetoothClass.Device.Major.PHONE:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_phone));
                break;
            case BluetoothClass.Device.Major.TOY:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_child));
                break;
            case BluetoothClass.Device.Major.WEARABLE:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_body));
                break;
            case BluetoothClass.Device.Major.UNCATEGORIZED:
            default:
                deviceIcon.setBackground(activity.getDrawable(R.drawable.icon_device_unknown));
                break;

        }

    }

    public void setLeftButtonText(String text) {
        leftButton.setText(text);
    }

    public void setRightButtonText(String text) {
        rightButton.setText(text);
    }

    public void setLeftButtonOnClick(View.OnClickListener onClick) {
        leftButton.setOnClickListener(onClick);
    }

    public void setRightButtonOnClick(View.OnClickListener onClick) {
        rightButton.setOnClickListener(onClick);
    }

    void setBaseElementsText() {
        deviceName.setText(bluetoothDevice.getName());
        deviceMAC.setText(bluetoothDevice.getAddress());
    }

    public void startFadeInAnimation() {

        AnimationSet animationSet = new AnimationSet(false);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(300);


        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0f, 1f,
                0f, 1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(400);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setDuration(400);

        startAnimation(animationSet);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.VISIBLE);
            }
        }, 390);

    }

    public DeviceElement(Activity activity, BluetoothDevice bluetoothDevice) {
        super(activity);
        this.activity = activity;
        this.bluetoothDevice = bluetoothDevice;

        scale = (int) getContext().getResources().getDisplayMetrics().density;

        createBaseElements();
        setStyles();
        startFadeInAnimation();
        setBaseElementsText();

        setIcon();

    }

    public DeviceElement(Activity activity, BluetoothDevice bluetoothDevice, int visibilityDelay) {
        super(activity);
        this.activity = activity;
        this.bluetoothDevice = bluetoothDevice;

        scale = (int) getContext().getResources().getDisplayMetrics().density;

        createBaseElements();
        setStyles();
        setBaseElementsText();

        setIcon();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startFadeInAnimation();
            }
        }, visibilityDelay);

    }

}
