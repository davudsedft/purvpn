package com.dabut.lib.v2ray;

import static android.Manifest.permission.POST_NOTIFICATIONS;
import static android.content.Context.RECEIVER_EXPORTED;
import static com.dabut.lib.v2ray.utils.V2rayConfigs.connectionState;
import static com.dabut.lib.v2ray.utils.V2rayConfigs.currentConfig;
import static com.dabut.lib.v2ray.utils.V2rayConfigs.serviceMode;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.SERVICE_TYPE_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_INTENT;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CONFIG_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.VpnService;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.dabut.lib.v2ray.core.V2rayCoreExecutor;
import com.dabut.lib.v2ray.interfaces.LatencyDelayListener;
import com.dabut.lib.v2ray.services.V2rayProxyService;
import com.dabut.lib.v2ray.services.V2rayVPNService;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConfigs;
import com.dabut.lib.v2ray.utils.V2rayConstants;

import java.util.ArrayList;
import java.util.Objects;

import libv2ray.Libv2ray;

public class V2rayController {
    static final BroadcastReceiver stateUpdaterBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                connectionState = (V2rayConstants.CONNECTION_STATES) Objects.requireNonNull(intent.getExtras()).getSerializable(SERVICE_CONNECTION_STATE_BROADCAST_EXTRA);
                if (Objects.equals(intent.getExtras().getString(SERVICE_TYPE_BROADCAST_EXTRA), V2rayProxyService.class.getSimpleName())) {
                    V2rayConfigs.serviceMode = V2rayConstants.SERVICE_MODES.PROXY_MODE;
                } else {
                    V2rayConfigs.serviceMode = V2rayConstants.SERVICE_MODES.VPN_MODE;
                }
            } catch (Exception ignore) {}
        }
    };

    public static void init(final AppCompatActivity activity, final int app_icon, final String app_name) {
        Utilities.copyAssets(activity);
        currentConfig.applicationIcon = app_icon;
        currentConfig.applicationName = app_name;
        registerReceivers(activity);

    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public static void registerReceivers(final Activity activity){
        try {
            activity.unregisterReceiver(stateUpdaterBroadcastReceiver);
        }catch (Exception ignore){}
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity.registerReceiver(stateUpdaterBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT), RECEIVER_EXPORTED);
        } else {
            activity.registerReceiver(stateUpdaterBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_STATICS_BROADCAST_INTENT));
        }
    }

    public static V2rayConstants.CONNECTION_STATES getConnectionState() {
        return connectionState;
    }

    public static boolean isPreparedForConnection(final Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) != PermissionChecker.PERMISSION_GRANTED) {
                return false;
            }
        }
        Intent vpnServicePrepareIntent = VpnService.prepare(context);
        return vpnServicePrepareIntent == null;
    }

    private static void prepareForConnection(final Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, POST_NOTIFICATIONS) != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{POST_NOTIFICATIONS}, 101);
                return;
            }
        }

    }

    public static void startV2ray(final Activity activity, final String remark, final String config, final ArrayList<String> blocked_apps) {
        if (!Utilities.refillV2rayConfig(remark, config, blocked_apps)) {
            return;
        }
        if (!isPreparedForConnection(activity)) {
            prepareForConnection(activity);
        } else {
            startTunnel(activity);
        }
    }

    public static void stopV2ray(final Context context) {
        Intent stop_intent = new Intent(V2RAY_SERVICE_COMMAND_INTENT);
        stop_intent.setPackage(context.getPackageName());
        stop_intent.putExtra(V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.STOP_SERVICE);
        context.sendBroadcast(stop_intent);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public static void getConnectedV2rayServerDelay(final Context context, final LatencyDelayListener latencyDelayCallback) {
        if (getConnectionState() != V2rayConstants.CONNECTION_STATES.CONNECTED) {
            latencyDelayCallback.OnResultReady(-1);
            return;
        }
        BroadcastReceiver connectionLatencyBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    int delay = Objects.requireNonNull(intent.getExtras()).getInt(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA);
                    latencyDelayCallback.OnResultReady(delay);
                } catch (Exception ignore) {
                    latencyDelayCallback.OnResultReady(-1);
                }
                context.unregisterReceiver(this);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT), RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(connectionLatencyBroadcastReceiver, new IntentFilter(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT));
        }
        Intent get_delay_intent = new Intent(V2RAY_SERVICE_COMMAND_INTENT);
        get_delay_intent.setPackage(context.getPackageName());
        get_delay_intent.putExtra(V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.MEASURE_DELAY);
        context.sendBroadcast(get_delay_intent);
    }
    
    public static long getV2rayServerDelay(final String config) {
        return V2rayCoreExecutor.getConfigDelay(Utilities.normalizeV2rayFullConfig(config));
    }

    public static String getCoreVersion() {
        return Libv2ray.checkVersionX();
    }

    public static void toggleConnectionMode() {
        if (serviceMode == V2rayConstants.SERVICE_MODES.PROXY_MODE) {
            serviceMode = V2rayConstants.SERVICE_MODES.VPN_MODE;
        } else {
            serviceMode = V2rayConstants.SERVICE_MODES.PROXY_MODE;
        }
    }

    public static void toggleTrafficStatics() {
        if (currentConfig.enableTrafficStatics) {
            currentConfig.enableTrafficStatics = false;
            currentConfig.enableTrafficStaticsOnNotification = false;
        } else {
            currentConfig.enableTrafficStatics = true;
            currentConfig.enableTrafficStaticsOnNotification = true;
        }
    }

    private static void startTunnel(final Context context) {
        Intent start_intent;
        if (serviceMode == V2rayConstants.SERVICE_MODES.PROXY_MODE) {
            start_intent = new Intent(context, V2rayProxyService.class);
        } else {
            start_intent = new Intent(context, V2rayVPNService.class);
        }
        start_intent.setPackage(context.getPackageName());
        start_intent.putExtra(V2RAY_SERVICE_COMMAND_EXTRA, V2rayConstants.SERVICE_COMMANDS.START_SERVICE);
        start_intent.putExtra(V2RAY_SERVICE_CONFIG_EXTRA, currentConfig);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            context.startForegroundService(start_intent);
        } else {
            context.startService(start_intent);
        }
    }

    @Deprecated
    public static boolean IsPreparedForConnection(final Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) != PermissionChecker.PERMISSION_GRANTED) {
                return false;
            }
        }
        Intent vpnServicePrepareIntent = VpnService.prepare(context);
        return vpnServicePrepareIntent == null;
    }

    @Deprecated
    public static void StartV2ray(final Context context, final String remark, final String config, final ArrayList<String> blocked_apps) {
        if (!Utilities.refillV2rayConfig(remark, config, blocked_apps)) {
            return;
        }
        startTunnel(context);
    }

    @Deprecated
    public static void StopV2ray(final Context context) {
        stopV2ray(context);
    }
}
