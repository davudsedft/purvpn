package com.dabut.purcow;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Locale;

import com.dabut.lib.v2ray.V2rayController;

public class Myproxy extends Service {
    String offerChannelId = "DEV7_DEV_V_E_CH_ID";


    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //  TLS_serv.start();

          //  pendingNotification();


        }
       // pendingNotification();
       // V2rayController.StopV2ray(getApplicationContext());

        V2rayController.toggleConnectionMode();

        return START_STICKY;

    }

    @Override
    public void onDestroy() {


      //  V2rayController.StopV2ray(getApplicationContext());


        V2rayController.toggleConnectionMode();
        Toast.makeText(this, getString(R.string.proxyoff), Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        V2rayController.toggleConnectionMode();
    }

    public void pendingNotification() {

        //  notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int color = Color.rgb(5, 64, 165);
        // stop.setAction(Intent.ACTION_STOP_FOREGROUND_SERVICE);
        //  stop.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        // stop.getAction().equals( Constants.ACTION.STOPFOREGROUND_ACTION);
        Intent intentHide = new Intent(getApplicationContext(), StopServiceReceiver2.class);
        //  Intent  servIntent = new Intent(this, MyService.class);
        //  servIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // servIntent.putExtra("stoppp" , "ffdd");
        // stopService(servIntent);


        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notifloop);


        notificationLayoutExpanded.setTextViewText(R.id.txtTitle2222,getString(R.string.myproxy));
        notificationLayoutExpanded.setTextViewText(R.id.uuuuubb,getString(R.string.off));

        // PendingIntent pStopSelf = PendingIntent.getService(this, 0, stop,0);

        notificationLayoutExpanded.setOnClickPendingIntent(R.id.uuuuubb,PendingIntent.getBroadcast(getApplicationContext(),0,intentHide,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE));
        Intent pIntent = new Intent(getApplicationContext(), MainActivity.class);

        //  PendingIntent mpIntent = PendingIntent.getActivity(getApplicationContext(),1, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent mpIntent2 = PendingIntent.getActivity(getApplicationContext(), 1, pIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder pNotifBuilder = new NotificationCompat.Builder(this, offerChannelId)
                .setSmallIcon(R.drawable.loopba)
                .setPriority(Notification.PRIORITY_HIGH)

                .setCustomContentView(notificationLayoutExpanded)
                .setContentIntent(mpIntent2)
                //.addAction(R.drawable.about,"Close Services",mpIntent2)
                //   .setOngoing(true)
                .setAutoCancel(false)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());


        // notifManager.notify(5, pNotifBuilder.build());
        startForeground(20, pNotifBuilder.build());

    }
    private void setApplicationLocale() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale("fa"));
        } else {
            config.setLocale(new Locale("fa"));
        }
        resources.updateConfiguration(config, dm);
    }
    private void setApplicationLocale2() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale("en"));
        } else {
            config.setLocale(new Locale("en"));
        }
        resources.updateConfiguration(config, dm);
    }


}