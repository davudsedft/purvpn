package com.dabut.purcow;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.RECEIVER_EXPORTED;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT;
import static com.dabut.purcow.Servers.Name2;
import static com.wireguard.android.backend.Tunnel.State.DOWN;
import static com.wireguard.android.backend.Tunnel.State.UP;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.dabut.lib.v2ray.V2rayController;
import com.dabut.lib.v2ray.utils.V2rayConstants;
import com.wireguard.android.backend.Backend;
import com.wireguard.android.backend.GoBackend;
import com.wireguard.android.backend.Tunnel;
import com.wireguard.config.Config;
import com.wireguard.config.InetEndpoint;
import com.wireguard.config.InetNetwork;
import com.wireguard.config.Interface;
import com.wireguard.config.Peer;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class MyWidgetProvider extends AppWidgetProvider  {
    private static final String SYNCE_CLICKED = "autumaticWidgetSyncButtonClick";
    private BroadcastReceiver v2rayBroadCastReceiver;
    String offerChannelId = "DEV7DEV_AXL_CH_ID";
    Backend backend = PersistentConnectionProperties.getInstance().getBackend();
    SharedPreferences wireguard,prefsFromLib2;
    ArrayList<String> selectedPackageList;
    Set<String> retrievedPackages;
    SharedPreferences  Config;

    private static  boolean newcon = false;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        Config  = context.getSharedPreferences("config", MODE_PRIVATE);
      String  nameconf = Config.getString(Name2, "");
        System.out.println("gppppppppppppppppppppppppppppppp");

//        final int count = appWidgetIds.length;
//        String status;
//
//        for (int i = 0; i < count; i++) {
//            int widgetId = appWidgetIds[i];
//            // ایجاد PendingIntent برای دکمه
//            Intent intent = new Intent(context, MyWidgetProvider.class);
//            intent.setAction("CLICKED");
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, widgetId, intent, PendingIntent.FLAG_IMMUTABLE);
//
//            // تنظیم کردن عملکرد دکمه با کلیک
//            views.setOnClickPendingIntent(R.id.myButton, pendingIntent);
//
//            // به‌روزرسانی ویجت
//            appWidgetManager.updateAppWidget(widgetId, views);
//        }
//


//
//        BroadcastReceiver v2rayBroadCastReceiver = new BroadcastReceiver() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//                RemoteViews remoteViews;
//                ComponentName componentName;
//
//                switch ((V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras().getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA))) {
//                    case CONNECTED:
//
//                        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//                        componentName = new ComponentName(context, MyWidgetProvider.class);
//                        remoteViews.setTextViewText(R.id.myButton, "on");
//                        remoteViews.setTextColor(R.id.myButton,Color.GREEN);
//                        //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
//
//                        appWidgetManager.updateAppWidget(componentName, remoteViews);
//
//
//
//
//                        break;
//                    case DISCONNECTED:
//                        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//                        componentName = new ComponentName(context, MyWidgetProvider.class);
//                        remoteViews.setTextViewText(R.id.myButton, "off");
//                        remoteViews.setTextColor(R.id.myButton,Color.RED);
//                        //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
//
//                        appWidgetManager.updateAppWidget(componentName, remoteViews);
//                        break;
//                    case CONNECTING:
//
//
//
//
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            context.registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
//        } else {
//            context.registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
//        }
//
//
//


if (nameconf.equals("wireguard")){

    v2rayBroadCastReceiver = new BroadcastReceiver() {
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
                    SharedPreferences Config = context.getSharedPreferences("config", MODE_PRIVATE);
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


        final int count = appWidgetIds.length;
        String status;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            // ایجاد PendingIntent برای دکمه
            RemoteViews remoteViews;
            ComponentName componentName;
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            componentName = new ComponentName(context, MyWidgetProvider.class);
            remoteViews.setOnClickPendingIntent(R.id.myButton, getPendingSelf(context,  SYNCE_CLICKED, widgetId));
            appWidgetManager.updateAppWidget(componentName, remoteViews);


        }


    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public void onReceive(Context context, @SuppressLint("UnsafeIntentLaunch") Intent intent) {
        super.onReceive(context, intent);
        System.out.println("byyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

        SharedPreferences   Config = context.getSharedPreferences("config", MODE_PRIVATE);

        String  nameconf = Config.getString(Name2, "");


        System.out.println(intent.getAction());


        try {
            backend.getRunningTunnelNames();
        }
        catch (NullPointerException e) {
            // backend cannot be created without context
            PersistentConnectionProperties.getInstance().setBackend(new GoBackend(context));
            backend = PersistentConnectionProperties.getInstance().getBackend();
        }

if (nameconf.equals("wireguard"))
{

    Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

    try {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews;
        ComponentName componentName;

        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
            newcon = true;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            componentName = new ComponentName(context, MyWidgetProvider.class);
            remoteViews.setTextColor(R.id.myButton,Color.GREEN);
            //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

            appWidgetManager.updateAppWidget(componentName, remoteViews);



            remoteViews.setTextViewText(R.id.textView,nameconf);


        }
        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == DOWN) {


            newcon = false;


            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            componentName = new ComponentName(context, MyWidgetProvider.class);
            remoteViews.setTextColor(R.id.myButton,Color.RED);
            remoteViews.setTextViewText(R.id.textView,nameconf);

            //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);

            appWidgetManager.updateAppWidget(componentName, remoteViews);


        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }


}else{


    v2rayBroadCastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews;
            ComponentName componentName;

            switch ((V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras().getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA))) {
                case CONNECTED:
                    System.out.println("xxxxxxxxxxxxxxx");
                    newcon = true;
                    remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                    componentName = new ComponentName(context, MyWidgetProvider.class);
                    remoteViews.setTextColor(R.id.myButton,Color.GREEN);
                    //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
                    remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

                    appWidgetManager.updateAppWidget(componentName, remoteViews);

                    SharedPreferences Config = context.getSharedPreferences("config", MODE_PRIVATE);
                    String nameconf = Config.getString(Name2, "");

                    remoteViews.setTextViewText(R.id.textView,nameconf);

                    break;
                case DISCONNECTED:
                    newcon = false;
                    Config = context.getSharedPreferences("config", MODE_PRIVATE);
                    nameconf = Config.getString(Name2, "");

                    remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                    componentName = new ComponentName(context, MyWidgetProvider.class);
                    remoteViews.setTextColor(R.id.myButton,Color.RED);
                    remoteViews.setTextViewText(R.id.textView,nameconf);

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
        context.registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
    } else {
        context.registerReceiver(v2rayBroadCastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
    }


}





        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews;
        ComponentName componentName;

        if (SYNCE_CLICKED.equals(intent.getAction())) {

            if (nameconf.equals("wireguard")){

                if (newcon){
                    Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

                    try {
                        backend.setState(tunnel, DOWN, null);

                        intent = new Intent(context,MyService2.class);
                        context.stopService(intent);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                    componentName = new ComponentName(context, MyWidgetProvider.class);
                    remoteViews.setTextColor(R.id.myButton,Color.RED);
                    //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
                    remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
                    appWidgetManager.updateAppWidget(componentName, remoteViews);




                }else{


                    try {
                        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == UP) {
                            Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();
                            intent = new Intent(context,MyService2.class);
                            context.stopService(intent);
                            backend.setState(tunnel, DOWN, null);

                            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                            componentName = new ComponentName(context, MyWidgetProvider.class);
                            remoteViews.setTextColor(R.id.myButton,Color.RED);
                            //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
                            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
                            appWidgetManager.updateAppWidget(componentName, remoteViews);






                        }
                        if (backend.getState(PersistentConnectionProperties.getInstance().getTunnel()) == DOWN) {

                            intent = new Intent(context,MyService2.class);
                            ContextCompat.startForegroundService(context,intent);

                            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                            componentName = new ComponentName(context, MyWidgetProvider.class);
                            remoteViews.setTextColor(R.id.myButton,Color.GREEN);
                            //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
                            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);



                            appWidgetManager.updateAppWidget(componentName, remoteViews);


                        }


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }




                }















            }else{


                boolean m= false;
                System.out.println(newcon);
                if (newcon){
                    V2rayController.stopV2ray(context);
                    System.out.println("uuuuuuuuuuuuu");


                }else {


                    switch (V2rayController.getConnectionState()) {
                        case CONNECTED:
                            V2rayController.stopV2ray(context.getApplicationContext());
                            System.out.println("fffffffffffffffffffffffffffff");

                            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                            componentName = new ComponentName(context, MyWidgetProvider.class);
                            remoteViews.setTextColor(R.id.myButton,Color.RED);
                            //  remoteViews.setImageViewResource(R.id.myButton,R.drawable.circle);
                            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.off);
                            appWidgetManager.updateAppWidget(componentName, remoteViews);

                            break;
                        case CONNECTING:
                            V2rayController.stopV2ray(context.getApplicationContext());
                            System.out.println("fffffffffffffffffffffffffffff");

                            break;
                        case DISCONNECTED:
                            System.out.println("bbbbbbbbbbb");




                            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
                            componentName = new ComponentName(context, MyWidgetProvider.class);
                            remoteViews.setTextColor(R.id.myButton,Color.GREEN);
                            //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
                            remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);


                            SharedPreferences Pref = context.getSharedPreferences("PrefFile", MODE_PRIVATE);
                            String text = Pref.getString(Servers.Name, "");




                            V2rayController.StartV2ray(context, "Default", text, null);




                            appWidgetManager.updateAppWidget(componentName, remoteViews);


                            break;
                        default:
                            V2rayController.StopV2ray(context.getApplicationContext());
                            break;


                    }



                }







            }






        }





















    }


    protected PendingIntent getPendingSelf(Context context, String action, int wigdetid) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);


        return PendingIntent.getBroadcast(context, wigdetid, intent, PendingIntent.FLAG_IMMUTABLE);

    }

    public void stab(Context context){

   //  SharedPreferences   prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);

            prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);

        retrievedPackages  = prefsFromLib2.getStringSet("selectedPackages", null);
        selectedPackageList   = new ArrayList<>(retrievedPackages);
        Backend backend = PersistentConnectionProperties.getInstance().getBackend();

        Tunnel tunnel = PersistentConnectionProperties.getInstance().getTunnel();

        Interface.Builder interfaceBuilder = new Interface.Builder();
        Peer.Builder peerBuilder = new Peer.Builder();
        wireguard = context.getSharedPreferences("adrw",MODE_PRIVATE);
        wireguard = context.getSharedPreferences("endw",MODE_PRIVATE);
        wireguard = context.getSharedPreferences("pubw",MODE_PRIVATE);
        wireguard = context.getSharedPreferences("priw",MODE_PRIVATE);

        String addr = wireguard.getString("adrw","");
        String end = wireguard.getString("endw","");
        String pub = wireguard.getString("pubw","");
        String pri = wireguard.getString("priw","");
        String mtu = "1280";
        String ips = "0.0.0.0/0";
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {



                        backend.setState(tunnel, UP, new Config.Builder()
                                .setInterface(interfaceBuilder.addAddress(InetNetwork.parse(addr)).parsePrivateKey(pri).setMtu(Integer.parseInt(mtu)).parseDnsServers("1.1.1.1").excludeApplications(selectedPackageList).build())
                                .addPeer(peerBuilder.addAllowedIp(InetNetwork.parse(ips)).setEndpoint(InetEndpoint.parse(end)).parsePublicKey(pub).build())
                                .build());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

public  static  void updat(Context context){


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
                    SharedPreferences Config = context.getSharedPreferences("config", MODE_PRIVATE);
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
                    V2rayController.stopV2ray(context.getApplicationContext());

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


    Toast.makeText(context, "hhhhhhhhgjhgj", Toast.LENGTH_SHORT).show();
}

    public  static  void updat2(Context context){

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews;
        ComponentName componentName;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        componentName = new ComponentName(context, MyWidgetProvider.class);
        remoteViews.setTextColor(R.id.myButton,Color.GREEN);
        //   remoteViews.setImageViewResource(R.id.myButton,R.drawable.kkk);
        remoteViews.setInt(R.id.myButton, "setBackgroundResource", R.drawable.on);

        appWidgetManager.updateAppWidget(componentName, remoteViews);

        SharedPreferences Config = context.getSharedPreferences("config", MODE_PRIVATE);
        String nameconf = Config.getString(Name2, "");

        remoteViews.setTextViewText(R.id.textView,nameconf);
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
                        SharedPreferences Config = context.getSharedPreferences("config", MODE_PRIVATE);
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
                        V2rayController.stopV2ray(context.getApplicationContext());

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