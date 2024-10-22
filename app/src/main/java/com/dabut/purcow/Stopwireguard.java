package com.dabut.purcow;

import static androidx.core.app.ActivityCompat.recreate;
import static com.wireguard.android.backend.Tunnel.State.DOWN;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.Tunnel;

public class Stopwireguard extends BroadcastReceiver {
    public static final int REQUEST_CODE = 5;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyService2.class);
        context.stopService(service);
        Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

        Backend backend = PersistentConnectionProperties.getInstance().getBackend();
        try {
            backend.setState(tunnel, DOWN, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        recreate(MainActivity.fa);


    }}