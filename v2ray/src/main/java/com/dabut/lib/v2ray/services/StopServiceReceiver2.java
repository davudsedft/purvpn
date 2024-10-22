package com.dabut.lib.v2ray.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dabut.lib.v2ray.V2rayController;

public class StopServiceReceiver2 extends BroadcastReceiver {

    public static final int REQUEST_CODE = 5;

    @Override
    public void onReceive(Context context, Intent intent) {
        V2rayController.stopV2ray(context);


    }
}