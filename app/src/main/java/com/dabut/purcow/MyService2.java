package com.dabut.purcow;

import static com.dabut.purcow.Servers.Name2;
import static com.wireguard.android.backend.Tunnel.State.DOWN;
import static com.wireguard.android.backend.Tunnel.State.UP;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.Tunnel;
import com.wireguard.config.Config;
import com.wireguard.config.InetEndpoint;
import com.wireguard.config.InetNetwork;
import com.wireguard.config.Interface;
import com.wireguard.config.Peer;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

public class MyService2 extends Service {

    SharedPreferences wireguard,prefsFromLib2,dns;
    ArrayList<String> selectedPackageList;
    Set<String> retrievedPackages;
    int port = 443;
    String cl ="loop.iranpurnet.ir";
    int bast = 180;
    double time = 0.01;
    String offerChannelId = "DEV7DEV_AXL_CH_ID";

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

        dns  = getSharedPreferences("newdns1", Context.MODE_MULTI_PROCESS);


    }

    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //  TLS_serv.start();
            createNotifChannel();
            pendingNotification();
            stab();

        }else {
            createNotifChannel();
            pendingNotification();
            stab();
        }


        return START_STICKY;

    }

    @SuppressLint({"WrongConstant", "NewApi"})
    @Override
    public void onDestroy() {
        simpleNotification();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews remoteViews;
        ComponentName componentName;



        simpleNotification();
        remoteViews = new RemoteViews(getPackageName(), R.layout.widget_layout);
        componentName = new ComponentName(this, MyWidgetProvider.class);
        remoteViews.setTextColor(R.id.myButton,Color.RED);
        //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
        remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
        remoteViews.setTextViewText(R.id.textView,"wireguard");

        appWidgetManager.updateAppWidget(componentName, remoteViews);

    }
    @SuppressLint("NewApi")
    public void stab(){

        Context context = this;
        prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);

        prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);

        retrievedPackages  = prefsFromLib2.getStringSet("selectedPackages", null);
        selectedPackageList   = new ArrayList<>(retrievedPackages);
        Backend backend = PersistentConnectionProperties.getInstance().getBackend();

        Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

        Interface.Builder interfaceBuilder = new Interface.Builder();
        Peer.Builder peerBuilder = new Peer.Builder();
        wireguard = getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = getSharedPreferences("priw",MODE_PRIVATE);
    SharedPreferences    Config = getSharedPreferences("config", Context.MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");
        String addr = wireguard.getString("adrw","");
        String end = wireguard.getString("endw","");


        Log.v("ddddddddddddddddddddd",end);
        String pub = wireguard.getString("pubw","");
        String pri = wireguard.getString("priw","");
        String mtu = "1280";
        String ips = "0.0.0.0/0";
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews;
                ComponentName componentName;
                try {
                    if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {


                        backend.setState(tunnel, DOWN, null);

                        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                        componentName = new ComponentName(context, MyWidgetProvider.class);
                        remoteViews.setTextColor(R.id.myButton,Color.RED);
                        //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
                        remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
                        remoteViews.setTextViewText(R.id.textView,nameconf);

                        appWidgetManager.updateAppWidget(componentName, remoteViews);

                    } else {

                        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                        componentName = new ComponentName(context, MyWidgetProvider.class);
                        remoteViews.setTextColor(R.id.myButton,Color.GREEN);


                        remoteViews.setTextViewText(R.id.textView,nameconf);
                        //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
                        remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

                        appWidgetManager.updateAppWidget(componentName, remoteViews);

                        backend.setState(tunnel, UP, new Config.Builder()
                                .setInterface(interfaceBuilder.addAddress(InetNetwork.parse(addr)).parsePrivateKey(pri).setMtu(Integer.parseInt(mtu)).parseDnsServers(dns.getString("newdns1","8.8.8.8")).excludeApplications(selectedPackageList).build())
                                .addPeer(peerBuilder.addAllowedIp(InetNetwork.parse(ips)).setEndpoint(InetEndpoint.parse(end)).parsePublicKey(pub).build())
                                .build());



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
    @SuppressLint("UnspecifiedImmutableFlag")
    public void simpleNotification() {
        NotificationManager   notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notifManager.cancel(20);


    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        simpleNotification();

    }
    public void mytun(String end , String pub ,String pri , String mtu , String add){

    }
    @SuppressLint("ForegroundServiceType")
    public void pendingNotification() {

        //  notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int color = Color.rgb(5, 64, 165);
        // stop.setAction(Intent.ACTION_STOP_FOREGROUND_SERVICE);
        //  stop.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        // stop.getAction().equals( Constants.ACTION.STOPFOREGROUND_ACTION);
        Intent intentHide = new Intent(getApplicationContext(), Stopwireguard.class);
        //  Intent  servIntent = new Intent(this, MyService.class);
        //  servIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // servIntent.putExtra("stoppp" , "ffdd");
        // stopService(servIntent);


        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notifloop);


        notificationLayoutExpanded.setTextViewText(R.id.txtTitle2222,"وایرگارد متصل");
        notificationLayoutExpanded.setTextViewText(R.id.uuuuubb,"خاموش");

        // PendingIntent pStopSelf = PendingIntent.getService(this, 0, stop,0);

        notificationLayoutExpanded.setOnClickPendingIntent(R.id.uuuuubb,PendingIntent.getBroadcast(getApplicationContext(),5,intentHide,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE));
        Intent pIntent = new Intent(getApplicationContext(), MainActivity.class);

        //  PendingIntent mpIntent = PendingIntent.getActivity(getApplicationContext(),1, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        PendingIntent mpIntent2 = PendingIntent.getActivity(getApplicationContext(), 1, pIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder pNotifBuilder = new NotificationCompat.Builder(this, offerChannelId)
                .setSmallIcon(com.dabut.lib.v2ray.R.drawable.ic_action_name)
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

    }}
