package com.dabut.purcow;


import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT;
import static com.dabut.purcow.MainActivity.notifManager;
import static com.dabut.purcow.Servers.Name;
import static com.dabut.purcow.Servers.Name2;
import static com.wireguard.android.backend.Tunnel.State.DOWN;
import static com.wireguard.android.backend.Tunnel.State.UP;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.dabut.lib.v2ray.V2rayController;
import com.dabut.lib.v2ray.utils.V2rayConstants;
import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.GoBackend;
import com.wireguard.android.backend.Tunnel;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MyQSTileService extends TileService {

    SharedPreferences Pref,Config;
    // Called when the user adds your tile.
    String offerChannelId = "DEV7DEV_AXL_CH_ID";
    Backend backend = PersistentConnectionProperties.getInstance().getBackend();


    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    // Called when your app can update your tile.
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public void onStartListening() {
        super.onStartListening();
        try {
            backend.getRunningTunnelNames();
        }
        catch (NullPointerException e) {
            // backend cannot be created without context
            PersistentConnectionProperties.getInstance().setBackend(new GoBackend(this));
            backend = PersistentConnectionProperties.getInstance().getBackend();
        }

        Tile tile = getQsTile();
        Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");

        if (nameconf.equals("wireguard")){
            Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

            try {
                if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {


                    tile.setState(Tile.STATE_ACTIVE);
                    tile.setLabel(nameconf);
                    tile.setContentDescription(tile.getLabel());
                    tile.updateTile();


                }
                if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == DOWN) {



                    tile.setState(Tile.STATE_INACTIVE);
                    tile.setLabel("پورکاو");
                    tile.setContentDescription(tile.getLabel());
                    tile.updateTile();

                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }else {

            switch (V2rayController.getConnectionState()) {
                case CONNECTED:
                    tile.setState(Tile.STATE_ACTIVE);
                    tile.setLabel(nameconf);
                    tile.setContentDescription(tile.getLabel());
                    tile.updateTile();
                    break;
                case DISCONNECTED:


                    tile.setState(Tile.STATE_INACTIVE);
                    tile.setLabel("پورکاو");
                    tile.setContentDescription(tile.getLabel());
                    tile.updateTile();
                    break;


            }

            BroadcastReceiver v2rayBroadCastReceiver = new BroadcastReceiver() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onReceive(Context context, Intent intent) {

                    switch ((V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras().getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA))) {
                        case CONNECTED:

                            tile.setState(Tile.STATE_ACTIVE);
                            tile.setLabel(nameconf);
                            tile.setContentDescription(tile.getLabel());
                            tile.updateTile();


                            break;
                        case DISCONNECTED:
                            tile.setState(Tile.STATE_INACTIVE);
                            tile.setLabel("پورکاو");
                            tile.setContentDescription(tile.getLabel());
                            tile.updateTile();
                            break;
                        case CONNECTING:

                            break;
                        default:
                            break;
                    }
                }
            };

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
            } else {
                registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
            }

        }






    }

    // Called when your app can no longer update your tile.
    @Override
    public void onStopListening() {
        super.onStopListening();

    updat2(this);


    }

    // Called when the user taps on your tile in an active or inactive state.
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public void onClick() {
        super.onClick();
        Pref = getSharedPreferences("PrefFile", Context.MODE_PRIVATE);
        String text = Pref.getString(Name, "");




        Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");
        Tile tile = getQsTile();
        if (tile.getLabel() == "پورکاو") {


            if (nameconf.equals("wireguard")){

                Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

                try {
                    if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                        backend.setState(tunnel, DOWN, null);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Intent intent = new Intent(this,MyService2.class);
                //  startService(intent);
                ContextCompat.startForegroundService(this, intent);

                tile.setLabel(nameconf);
                tile.setState(Tile.STATE_ACTIVE);
                tile.setContentDescription(tile.getLabel());
                tile.updateTile();
            }else {
                tile.setLabel(nameconf);
                tile.setState(Tile.STATE_ACTIVE);

                V2rayController.StartV2ray(this, "Default", text, null);
                tile.setContentDescription(tile.getLabel());
                tile.updateTile();

            }




        } else {
            if (nameconf.equals("wireguard")){
                Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

                try {
                    if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                        backend.setState(tunnel, DOWN, null);

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }




                Intent intent = new Intent(this,MyService2.class);
                stopService(intent);
                tile.setLabel("پورکاو");
                tile.setState(Tile.STATE_INACTIVE);
                tile.setContentDescription(tile.getLabel());
                tile.updateTile();
            }else {

                V2rayController.StopV2ray(this);
                tile.setLabel("پورکاو");
                tile.setState(Tile.STATE_INACTIVE);
                tile.setContentDescription(tile.getLabel());
                tile.updateTile();
            }


        }





    }

    @SuppressLint("UnspecifiedImmutableFlag")
    public void simpleNotification() {
        notifManager.cancel(8);


    }

    // Called when the user removes your tile.
    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }
    private void createNotifChannel() {
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent pIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent mpIntent = PendingIntent.getActivity(getApplicationContext(), 0, pIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "purnet v3ray";
            String offerChannelDescription = "best free vpn";
            int offerChannelImportance = NotificationManager.IMPORTANCE_LOW;

            @SuppressLint("WrongConstant") NotificationChannel notifChannel = new NotificationChannel(offerChannelId, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            //notifChannel.enableVibration(true);
            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.GREEN);

            notifManager.createNotificationChannel(notifChannel);

        }
    }

    public  static  void updat2(Context context){
        Backend backend = PersistentConnectionProperties.getInstance().getBackend();

      SharedPreferences  Config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews;
        ComponentName componentName;
if (nameconf.equals("wireguard")){
    try {
        backend.getRunningTunnelNames();
    }
    catch (NullPointerException e) {
        // backend cannot be created without context
        PersistentConnectionProperties.getInstance().setBackend(new GoBackend(context));
        backend = PersistentConnectionProperties.getInstance().getBackend();
    }


    Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

    try {
        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            componentName = new ComponentName(context, MyWidgetProvider.class);
            remoteViews.setTextColor(R.id.myButton,Color.GREEN);


            remoteViews.setTextViewText(R.id.textView,nameconf);
            //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

            appWidgetManager.updateAppWidget(componentName, remoteViews);

        }
        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == DOWN) {

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            componentName = new ComponentName(context, MyWidgetProvider.class);
            remoteViews.setTextColor(R.id.myButton,Color.RED);
            //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
            remoteViews.setTextViewText(R.id.textView,nameconf);

            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }

}else {

    BroadcastReceiver  v2rayBroadCastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews;
            ComponentName componentName;
            switch ((V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras().getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA))) {
                case CONNECTED:
                    System.out.println("mmmmmmmmmmmmm");

                    remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                    componentName = new ComponentName(context, MyWidgetProvider.class);
                    remoteViews.setTextColor(R.id.myButton,Color.GREEN);
                    SharedPreferences Config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
                    String nameconf = Config.getString(Name2, "");

                    remoteViews.setTextViewText(R.id.textView,nameconf);
                    //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
                    remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

                    appWidgetManager.updateAppWidget(componentName, remoteViews);




                    break;
                case DISCONNECTED:
                    remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                    componentName = new ComponentName(context, MyWidgetProvider.class);
                    remoteViews.setTextColor(R.id.myButton,Color.RED);
                    //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
                    remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);

                    appWidgetManager.updateAppWidget(componentName, remoteViews);
                    break;

                default:
                    break;
            }
        }
    };

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getApplicationContext().registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
    } else {
        context.getApplicationContext().registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
    }


}


    }

}
