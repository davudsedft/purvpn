package com.dabut.lib.v2ray.services;

import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_INTENT;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dabut.lib.v2ray.core.V2rayCoreExecutor;
import com.dabut.lib.v2ray.interfaces.StateListener;
import com.dabut.lib.v2ray.interfaces.V2rayServicesListener;
import com.dabut.lib.v2ray.model.V2rayConfigModel;
import com.dabut.lib.v2ray.utils.V2rayConstants;

public class V2rayProxyService extends Service implements V2rayServicesListener {
    private V2rayCoreExecutor v2rayCoreExecutor;
    private NotificationService notificationService;
    private StaticsBroadCastService staticsBroadCastService;
    private V2rayConstants.CONNECTION_STATES connectionState = V2rayConstants.CONNECTION_STATES.DISCONNECTED;
    private V2rayConfigModel currentConfig = new V2rayConfigModel();
    private boolean isServiceCreated = false;

    private final BroadcastReceiver serviceCommandBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                V2rayConstants.SERVICE_COMMANDS serviceCommand = (V2rayConstants.SERVICE_COMMANDS) intent.getSerializableExtra(V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA);
                if (serviceCommand == null) {
                    return;
                }
                switch (serviceCommand) {
                    case STOP_SERVICE:
                        if (v2rayCoreExecutor != null) {
                            v2rayCoreExecutor.stopCore(true);
                        }
                        break;
                    case MEASURE_DELAY:
                        if (v2rayCoreExecutor != null) {
                            v2rayCoreExecutor.broadCastCurrentServerDelay();
                        }
                        break;
                    default:
                        break;
                }
            }catch (Exception ignore){}
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if (!isServiceCreated) {
            connectionState = V2rayConstants.CONNECTION_STATES.CONNECTING;
            v2rayCoreExecutor = new V2rayCoreExecutor(this);
            notificationService = new NotificationService(this);
            staticsBroadCastService = new StaticsBroadCastService(this, new StateListener() {
                @Override
                public V2rayConstants.CONNECTION_STATES getConnectionState() {
                    return connectionState;
                }

                @Override
                public V2rayConstants.CORE_STATES getCoreState() {
                    if (v2rayCoreExecutor == null) {
                        return V2rayConstants.CORE_STATES.IDLE;
                    }
                    return v2rayCoreExecutor.getCoreState();
                }

                @Override
                public long getDownloadSpeed() {
                    if (v2rayCoreExecutor == null) {
                        return -1;
                    }
                    return v2rayCoreExecutor.getDownloadSpeed();
                }

                @Override
                public long getUploadSpeed() {
                    if (v2rayCoreExecutor == null) {
                        return -1;
                    }
                    return v2rayCoreExecutor.getUploadSpeed();
                }
            });
            isServiceCreated = true;
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            V2rayConstants.SERVICE_COMMANDS serviceCommand = (V2rayConstants.SERVICE_COMMANDS) intent.getSerializableExtra(V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA);
            if (serviceCommand == null) {
                return super.onStartCommand(intent, flags, startId);
            }
            switch (serviceCommand) {
                case STOP_SERVICE:
                    v2rayCoreExecutor.stopCore(true);
                    break;
                case START_SERVICE:
                    currentConfig = (V2rayConfigModel) intent.getSerializableExtra(V2rayConstants.V2RAY_SERVICE_CONFIG_EXTRA);
                    if (currentConfig == null) {
                        stopService();
                        break;
                    }
                    staticsBroadCastService.isTrafficStaticsEnabled = currentConfig.enableTrafficStatics;
                    if (currentConfig.enableTrafficStatics && currentConfig.enableTrafficStaticsOnNotification) {
                        staticsBroadCastService.trafficListener = notificationService.trafficListener;
                    }
                    v2rayCoreExecutor.startCore(currentConfig);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        registerReceiver(serviceCommandBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_COMMAND_INTENT), RECEIVER_EXPORTED);
                    } else {
                        registerReceiver(serviceCommandBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_COMMAND_INTENT));
                    }
                    return START_STICKY;
                default:
                    onDestroy();
                    break;
            }
        }catch (Exception ignore){}
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(serviceCommandBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onProtect(int socket) {
        return true;
    }

    @Override
    public Service getService() {
        return this;
    }

    @Override
    public void startService() {
        connectionState = V2rayConstants.CONNECTION_STATES.CONNECTED;
        notificationService.setConnectedNotification(currentConfig.remark, currentConfig.applicationIcon);
        staticsBroadCastService.start();
    }

    @Override
    public void stopService() {
        try {
            staticsBroadCastService.sendDisconnectedBroadCast(this);
            staticsBroadCastService.stop();
            notificationService.dismissNotification();
            stopForeground(true);
            stopSelf();
        } catch (Exception e) {
            Log.d(V2rayProxyService.class.getSimpleName(), "stopService => ", e);
        }
    }
}
