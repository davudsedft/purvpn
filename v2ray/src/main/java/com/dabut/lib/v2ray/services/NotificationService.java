package com.dabut.lib.v2ray.services;

import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE;
import static com.dabut.lib.v2ray.services.V2rayVPNService.Name2;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_OPENED_APPLICATION_INTENT;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.dabut.lib.v2ray.R;
import com.dabut.lib.v2ray.interfaces.TrafficListener;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConstants;

import java.util.Objects;

public class NotificationService {
    private NotificationManager mNotificationManager = null;
    private final NotificationCompat.Builder notifcationBuilder;
    public boolean isNotificationOnGoing;
    private final int NOTIFICATION_ID = 1;

    public  TrafficListener trafficListener = new TrafficListener() {
        @Override
        public void onTrafficChanged(long uploadSpeed, long downloadSpeed, long uploadedTraffic, long downloadedTraffic , Context context) {
            if (mNotificationManager != null && notifcationBuilder != null) {
                if (isNotificationOnGoing) {
                    SharedPreferences Config = context.getSharedPreferences("config", Context.MODE_MULTI_PROCESS);
                    String nameconf = Config.getString(Name2, "lllll");

                    RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification);
                    notificationLayout.setTextViewText(R.id.txtconfig,nameconf);

                    // notificationLayout.setTextViewText(R.id.txtnotspeed, "ترافیک ↓" + Utilities.parseTraffic(downloadedTraffic, false, false));
                    notificationLayout.setTextViewText(R.id.txttrafff," ↓ " + Utilities.parseTraffic(downloadedTraffic, false, false) + " |  ↑  " + Utilities.parseTraffic(uploadedTraffic, false, false));
                   // notifcationBuilder.setContentText(  Utilities.parseTraffic(downloadSpeed, false, true) + " | اپلود : ↑" + Utilities.parseTraffic(uploadSpeed, false, true));
                    notificationLayout.setTextViewText(R.id.speeeeddd," ↓ " +Utilities.parseTraffic(downloadSpeed, false, true) + " |  ↑" + Utilities.parseTraffic(uploadSpeed, false, true));
                    Intent disconnectIntent = new Intent(context, context.getClass());
                    disconnectIntent.setPackage(context.getPackageName());
                    disconnectIntent.putExtra(V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.STOP_SERVICE);
                    PendingIntent disconnectPendingIntent = PendingIntent.getService(context, 0, disconnectIntent, judgeForNotificationFlag());
                    notificationLayout.setOnClickPendingIntent(R.id.button,PendingIntent.getService(context,0,disconnectIntent,PendingIntent.FLAG_UPDATE_CURRENT| PendingIntent.FLAG_IMMUTABLE));

                    notifcationBuilder.setCustomContentView(notificationLayout);

                    mNotificationManager.notify(NOTIFICATION_ID, notifcationBuilder.build());
                }
            }
        }
    };


    public NotificationService(final Service targetService ) {
        String packageName = targetService.getPackageName();
        RemoteViews notificationLayout = new RemoteViews(targetService.getPackageName(), R.layout.notification);

// Set the uploaded traffic text
        Intent launchIntent = targetService.getPackageManager().
                getLaunchIntentForPackage(targetService.getApplicationInfo().packageName);
        assert launchIntent != null;
        launchIntent.setAction(V2RAY_SERVICE_OPENED_APPLICATION_INTENT);
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent notificationContentPendingIntent = PendingIntent.getActivity(targetService, 0, launchIntent, judgeForNotificationFlag());
        String notificationChannelID = "";
        String applicationName = "unknown_name";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                PackageManager pm = targetService.getApplicationContext().getPackageManager();
                ApplicationInfo ai;
                ai = pm.getApplicationInfo(targetService.getPackageName(), 0);
                applicationName = (String) pm.getApplicationLabel(ai);
                notificationChannelID = createNotificationChannelID(targetService, applicationName);
            } catch (Exception e) {
                notificationChannelID = createNotificationChannelID(targetService, applicationName);
            }
        }


        notifcationBuilder = new NotificationCompat.Builder(targetService, notificationChannelID);
        notifcationBuilder
                .setSmallIcon(R.drawable.ic_action_name)
                .setShowWhen(false)
                .setOnlyAlertOnce(true)
                .setContentIntent(notificationContentPendingIntent)
                .setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            notifcationBuilder.setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE);
        } else {
            notifcationBuilder.setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_DEFAULT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            targetService.startForeground(NOTIFICATION_ID, notifcationBuilder.build(), FOREGROUND_SERVICE_TYPE_SPECIAL_USE);
        } else {
            targetService.startForeground(NOTIFICATION_ID, notifcationBuilder.build());
        }
        isNotificationOnGoing = true;
    }

    private NotificationManager getNotificationManager(final Service targetService) {
        if (mNotificationManager == null) {
            try {
                mNotificationManager = (NotificationManager) targetService.getSystemService(Context.NOTIFICATION_SERVICE);
            } catch (Exception e) {
                return null;
            }
        }
        return mNotificationManager;
    }

    public void setConnectedNotification(String remark, int iconResource) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createNotificationChannelID(final Service targetService, final String Application_name) {
        String notification_channel_id = "DEV7DEV_AXL_CH_ID";
        NotificationChannel notificationChannel = new NotificationChannel(
                notification_channel_id, Application_name + " Background Service", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setLightColor(Color.BLUE);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationChannel.setImportance(NotificationManager.IMPORTANCE_NONE);
        Objects.requireNonNull(getNotificationManager(targetService)).createNotificationChannel(notificationChannel);
        return notification_channel_id;
    }

    @SuppressLint("ObsoleteSdkInt")
    private int judgeForNotificationFlag() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT;
        } else {
            return PendingIntent.FLAG_UPDATE_CURRENT;
        }
    }

    public void dismissNotification() {
        if (mNotificationManager != null) {
            isNotificationOnGoing = false;
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    }

}
