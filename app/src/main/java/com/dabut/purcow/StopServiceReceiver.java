package com.dabut.purcow;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StopServiceReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 5;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyService.class);
     context.stopService(service);
       recreate(MainActivity.fa);



    }}