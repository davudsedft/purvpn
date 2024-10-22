package com.dabut.purcow;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.dabut.purcow.Doh.DoH_over_Fragment;
import com.dabut.purcow.Doh.HTTPS_Fragmentor;
import com.dabut.purcow.Doh.TLS_Fragmentor;

import java.util.Locale;

public class MyService extends Service {


    int port = 443;
    String cl ="loop.iranpurnet.ir";
    int bast = 180;
    double time = 0.01;
    String offerChannelId = "DEV7DEV_AXL_CH_ID";
    TLS_Fragmentor TLS_serv;
    DoH_over_Fragment DoH_service;

    HTTPS_Fragmentor HTTPS_serv;
    @Override
    public void onCreate() {
        boolean lang = true;
        SharedPreferences preferences2 = getSharedPreferences("language", MODE_PRIVATE);

        if (preferences2.contains("lang")) {
            lang = preferences2.getBoolean("lang", true);


        }

        // اگر تم فعلی روشن است، تم را روشن کنید و برعکس
        if (lang) {
            setApplicationLocale();



        } else {
            setApplicationLocale2();

        }

        SharedPreferences   fport  = getSharedPreferences("portloop", Context.MODE_MULTI_PROCESS);
        SharedPreferences   ffrag  = getSharedPreferences("fragment", Context.MODE_MULTI_PROCESS);
        SharedPreferences   ftime  = getSharedPreferences("timeloop", Context.MODE_MULTI_PROCESS);
        SharedPreferences   cloud  = getSharedPreferences("clodfler", Context.MODE_MULTI_PROCESS);

        String m = fport.getString("portloop" , "");
         cl = cloud.getString("clodfler" ,"");
        String m2 = ffrag.getString("fragment" , "");
        String m3 = ftime.getString("timeloop" , "");
        Log.v("jjjj" , m3);
        if (m.equals("")){
            port = 443;
        }else {
            port = Integer.parseInt(m);
        }
        if (m2.equals("")){
            bast = 180;
        }else {
            bast = Integer.parseInt(m2);
        }
        if (m3.equals("")){
            time = 0.001;
        }else {
            time = Double.parseDouble(m3);
            time = time/100000;
        }
        if (cl.equals("")){
            cl ="loop.iranpurnet.ir";
        }else {
            cl = cloud.getString("clodfler" ,"");

        }
        TLS_serv    = new TLS_Fragmentor("127.0.0.1" ,2012 ,
                cl ,port,
                true , bast , time);



        DoH_service    = new DoH_over_Fragment("cloudflare",
                cl, port,
                true, bast, time);


        HTTPS_serv  = new HTTPS_Fragmentor("127.0.0.1",2015,
                null, -1,
                DoH_service,true,
                bast,0.01);



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, getString(R.string.loopstrt), Toast.LENGTH_SHORT).show();
        Log.v("aaa",Integer.toString(port));
        Log.v("aaa",Integer.toString(bast));
        Log.v("aaa",Double.toString(time));
        Log.v("aaa",cl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //  TLS_serv.start();
            createNotifChannel();
            pendingNotification();


        }
        createNotifChannel();
        pendingNotification();

        TLS_serv.start();
        HTTPS_serv.start();

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        TLS_serv.safely_stop_server();
      //  V2rayController.StopV2ray(getApplicationContext());


        Toast.makeText(this, getString(R.string.lppstop), Toast.LENGTH_SHORT).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @SuppressLint("ForegroundServiceType")
    public void pendingNotification() {

        //  notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int color = Color.rgb(5, 64, 165);
        // stop.setAction(Intent.ACTION_STOP_FOREGROUND_SERVICE);
        //  stop.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        // stop.getAction().equals( Constants.ACTION.STOPFOREGROUND_ACTION);
        Intent intentHide = new Intent(getApplicationContext(), StopServiceReceiver.class);
        //  Intent  servIntent = new Intent(this, MyService.class);
        //  servIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // servIntent.putExtra("stoppp" , "ffdd");
        // stopService(servIntent);


        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notifloop);


        notificationLayoutExpanded.setTextViewText(R.id.txtTitle2222,getString(R.string.ejraloop));
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
        startForeground(30, pNotifBuilder.build());

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

    private void createNotifChannel() {
        NotificationManager   notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent pIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent mpIntent = PendingIntent.getActivity(getApplicationContext(), 0, pIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String offerChannelName = "purnet v3ray";
            String offerChannelDescription = "best free vpn";
            int offerChannelImportance = NotificationManager.IMPORTANCE_MAX;

            @SuppressLint("WrongConstant") NotificationChannel notifChannel = new NotificationChannel(offerChannelId, offerChannelName, offerChannelImportance);
            notifChannel.setDescription(offerChannelDescription);
            //notifChannel.enableVibration(true);
            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.GREEN);

            notifManager.createNotificationChannel(notifChannel);

        }

    }
}