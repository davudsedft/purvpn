package com.dabut.lib.v2ray.services;


import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CORE_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_DURATION_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_TYPE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;

import com.dabut.lib.v2ray.interfaces.StateListener;
import com.dabut.lib.v2ray.interfaces.TrafficListener;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConstants;


public class StaticsBroadCastService {
    private String SERVICE_DURATION = "00:00:00";
    private int seconds, minutes, hours;
    private long totalDownload, totalUpload, uploadSpeed, downloadSpeed;
    private final CountDownTimer countDownTimer;
    public boolean isTrafficStaticsEnabled;
    public boolean isCounterStarted;
    public TrafficListener trafficListener;

    public StaticsBroadCastService(final Context targetService, final StateListener stateListener) {
        resetCounter();
        isCounterStarted = false;
        countDownTimer = new CountDownTimer(604800000, 1000) {
            @SuppressLint("ObsoleteSdkInt")
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onTick(long millisUntilFinished) {
                seconds++;
                if (seconds == 59) {
                    minutes++;
                    seconds = 0;
                }
                if (minutes == 59) {
                    minutes = 0;
                    hours++;
                }
                if (hours == 23) {
                    hours = 0;
                }
                if (isTrafficStaticsEnabled) {
                    downloadSpeed = stateListener.getDownloadSpeed();
                    uploadSpeed = stateListener.getUploadSpeed();
                    totalDownload = totalDownload + downloadSpeed;
                    totalUpload = totalUpload + uploadSpeed;
                    if (trafficListener != null) {
                        trafficListener.onTrafficChanged(uploadSpeed, downloadSpeed, totalUpload, totalDownload , targetService);
                    }
                }
                SERVICE_DURATION = Utilities.convertIntToTwoDigit(hours) + ":" + Utilities.convertIntToTwoDigit(minutes) + ":" + Utilities.convertIntToTwoDigit(seconds);
                sendBroadCast(targetService, stateListener);
            }

            public void onFinish() {
                countDownTimer.cancel();
                new StaticsBroadCastService(targetService, stateListener).start();
            }
        };
    }

    public void resetCounter() {
        SERVICE_DURATION = "00:00:00";
        seconds = 0;
        minutes = 0;
        hours = 0;
        uploadSpeed = 0;
        downloadSpeed = 0;
    }

    public void start() {
        if (!isCounterStarted) {
            countDownTimer.start();
            isCounterStarted = true;
        }
    }

    public void stop() {
        if (isCounterStarted) {
            isCounterStarted = false;
            countDownTimer.cancel();
        }
    }

    public void sendBroadCast(final Context targetService, final StateListener stateListener) {
        Intent connection_info_intent = new Intent(V2RAY_SERVICE_STATICS_BROADCAST_INTENT);
        connection_info_intent.setPackage(targetService.getPackageName());
        connection_info_intent.putExtra(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA, stateListener.getConnectionState());
        connection_info_intent.putExtra(SERVICE_CORE_STATE_BROADCAST_EXTRA, stateListener.getCoreState());
        connection_info_intent.putExtra(SERVICE_DURATION_BROADCAST_EXTRA, SERVICE_DURATION);
        connection_info_intent.putExtra(SERVICE_TYPE_BROADCAST_EXTRA, targetService.getClass().getSimpleName());
        connection_info_intent.putExtra(SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA, Utilities.parseTraffic(uploadSpeed, false, true));
        connection_info_intent.putExtra(SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA, Utilities.parseTraffic(downloadSpeed, false, true));
        connection_info_intent.putExtra(SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA, Utilities.parseTraffic(totalUpload, false, false));
        connection_info_intent.putExtra(SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA, Utilities.parseTraffic(totalDownload, false, false));
        targetService.sendBroadcast(connection_info_intent);
    }

    public void sendDisconnectedBroadCast(final Context targetService) {
        resetCounter();
        Intent connection_info_intent = new Intent(V2RAY_SERVICE_STATICS_BROADCAST_INTENT);
        connection_info_intent.setPackage(targetService.getPackageName());
        connection_info_intent.putExtra(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA, V2rayConstants.CONNECTION_STATES.DISCONNECTED);
        connection_info_intent.putExtra(SERVICE_CORE_STATE_BROADCAST_EXTRA, V2rayConstants.CORE_STATES.STOPPED);
        connection_info_intent.putExtra(SERVICE_TYPE_BROADCAST_EXTRA, targetService.getClass().getSimpleName());
        connection_info_intent.putExtra(SERVICE_DURATION_BROADCAST_EXTRA, "00:00:00");
        connection_info_intent.putExtra(SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA, "0.0 B/s");
        connection_info_intent.putExtra(SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA, "0.0 B/s");
        connection_info_intent.putExtra(SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA, "0.0 B");
        connection_info_intent.putExtra(SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA, "0.0 B");
        targetService.sendBroadcast(connection_info_intent);
    }

}
