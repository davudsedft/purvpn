package com.dabut.lib.v2ray.services;

import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_COMMAND_INTENT;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dabut.lib.v2ray.core.Tun2SocksExecutor;
import com.dabut.lib.v2ray.core.V2rayCoreExecutor;
import com.dabut.lib.v2ray.interfaces.StateListener;
import com.dabut.lib.v2ray.interfaces.Tun2SocksListener;
import com.dabut.lib.v2ray.interfaces.V2rayServicesListener;
import com.dabut.lib.v2ray.model.V2rayConfigModel;
import com.dabut.lib.v2ray.utils.V2rayConstants;

import java.io.File;
import java.io.FileDescriptor;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

public class V2rayVPNService extends VpnService implements V2rayServicesListener, Tun2SocksListener {
    private ParcelFileDescriptor tunnelInterface;
    public static final String Name2 = "Key";

    ArrayList<String> selectedPackageList;
    Set<String> retrievedPackages;
    SharedPreferences prefsFromLib2,dns1,dns2;
    private Tun2SocksExecutor tun2SocksExecutor;
    private V2rayCoreExecutor v2rayCoreExecutor;
    private NotificationService notificationService;
    private StaticsBroadCastService staticsBroadCastService;
    private V2rayConstants.CONNECTION_STATES connectionState = V2rayConstants.CONNECTION_STATES.DISCONNECTED;
    private V2rayConfigModel currentConfig = new V2rayConfigModel();
    private boolean isServiceCreated = false;
    private boolean isServiceSetupStarted = false;


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
            } catch (Exception ignore) {
            }
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();

        if (!isServiceCreated) {
            connectionState = V2rayConstants.CONNECTION_STATES.CONNECTING;
            StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(threadPolicy);
            tun2SocksExecutor = new Tun2SocksExecutor(this);
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
            V2rayConstants.SERVICE_COMMANDS serviceCommand = (V2rayConstants.SERVICE_COMMANDS) intent.getSerializableExtra(V2RAY_SERVICE_COMMAND_EXTRA);
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
        } catch (Exception ignore) {
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRevoke() {
        stopService();
    }

    private void setupService() {
        if (isServiceSetupStarted) {
            return;
        } else {
            isServiceSetupStarted = true;
        }
        try {
            if (tunnelInterface != null) {
                tunnelInterface.close();
            }
        } catch (Exception ignore) {
        }
        Intent prepare_intent = prepare(this);
        if (prepare_intent != null) {
            return;
        }
        Builder builder = getBuilder();
        try {
//            builder.addDisallowedApplication(getPackageName());
            tunnelInterface = builder.establish();
            int localDNSPort = 0;
            if (currentConfig.enableLocalTunneledDNS){
                localDNSPort = currentConfig.localDNSPort;
            }
            tun2SocksExecutor.run(this, currentConfig.localSocksPort,localDNSPort );
            sendFileDescriptor();
            if (tun2SocksExecutor.isTun2SucksRunning()) {
                connectionState = V2rayConstants.CONNECTION_STATES.CONNECTED;
                notificationService.setConnectedNotification(currentConfig.remark, currentConfig.applicationIcon);
                staticsBroadCastService.start();
            }
        } catch (Exception e) {
            Log.e(V2rayVPNService.class.getSimpleName(), "setupFailed => ", e);
            stopService();
        }
    }

    @NonNull
    private Builder getBuilder() {

        Context context = this;
        prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);
        SharedPreferences Config = context.getSharedPreferences("config", Context.MODE_MULTI_PROCESS);

        String con = Config.getString(Name2,"");

        SharedPreferences.Editor shEdit2 = Config.edit();
        shEdit2.putString(Name2, con);
        shEdit2.apply();
        prefsFromLib2  = context.getSharedPreferences("com.dabut.purnetvray", Context.MODE_MULTI_PROCESS);
        dns1  = context.getSharedPreferences("newdns1", Context.MODE_MULTI_PROCESS);

        retrievedPackages  = prefsFromLib2.getStringSet("selectedPackages", null);
        selectedPackageList   = new ArrayList<>(retrievedPackages);
        //  String p = selectedPackageList.get(0);
        String d1 = dns1.getString("dns1is", null);

        if (d1==null || d1 ==""){
            d1 = "1.1.1.1";
        }

        Builder builder = new Builder();
        builder.setSession(currentConfig.remark);
        builder.setMtu(1500);
        builder.addAddress("26.26.26.1", 30);
        builder.addRoute("0.0.0.0", 0);
        if (currentConfig.enableLocalTunneledDNS) {
            builder.addDnsServer("26.26.26.2");
        } else {
            builder.addDnsServer("1.1.1.1");
            builder.addDnsServer("8.8.8.8");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            builder.setMetered(false);
        }
        if (selectedPackageList.size()>0) {
            for (int u = 0; u < selectedPackageList.size(); u++) {
                Log.v("gggggggg", selectedPackageList.get(u));

                try {
                    builder.addDisallowedApplication(selectedPackageList.get(u));
                } catch (PackageManager.NameNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return builder;
    }

    private void sendFileDescriptor() {
        String localSocksFile = new File(getApplicationContext().getFilesDir(), "sock_path").getAbsolutePath();
        FileDescriptor tunFd = tunnelInterface.getFileDescriptor();
        new Thread(() -> {
            boolean isSendFDSuccess = false;
            for (int sendFDTries = 0; sendFDTries < 5; sendFDTries++) {
                try {
                    Thread.sleep(50L * sendFDTries);
                    LocalSocket clientLocalSocket = new LocalSocket();
                    clientLocalSocket.connect(new LocalSocketAddress(localSocksFile, LocalSocketAddress.Namespace.FILESYSTEM));
                    if (!clientLocalSocket.isConnected()) {
                        Log.i("SOCK_FILE", "Unable to connect to localSocksFile [" + localSocksFile + "]");
                    } else {
                        Log.i("SOCK_FILE", "connected to sock file [" + localSocksFile + "]");
                    }
                    OutputStream clientOutStream = clientLocalSocket.getOutputStream();
                    clientLocalSocket.setFileDescriptorsForSend(new FileDescriptor[]{tunFd});
                    clientOutStream.write(42);
//                    clientLocalSocket.setFileDescriptorsForSend(null);
                    clientLocalSocket.shutdownOutput();
                    clientLocalSocket.close();
                    isSendFDSuccess = true;
                    break;
                } catch (Exception ignore) {
                }
            }
            if (!isSendFDSuccess) {
                Log.w("SendFDFailed", "Could`nt send file descriptor !");
            }
        }, "sendFd_Thread").start();
    }

    @Override
    public void onDestroy() {



        unregisterReceiver(serviceCommandBroadcastReceiver);
        super.onDestroy();

    }

    @Override
    public boolean onProtect(int socket) {
        return protect(socket);
    }

    @Override
    public Service getService() {
        return this;
    }

    @Override
    public void startService() {
        setupService();
    }

    @Override
    public void stopService() {
        try {
            staticsBroadCastService.sendDisconnectedBroadCast(this);
            tun2SocksExecutor.stopTun2Socks();
            staticsBroadCastService.stop();
            notificationService.dismissNotification();
            stopForeground(true);
            stopSelf();
            try {
                tunnelInterface.close();
            } catch (Exception ignore) {

            }
        } catch (Exception e) {
            Log.d(V2rayVPNService.class.getSimpleName(), "stopService => ", e);
        }
    }

    @Override
    public void OnTun2SocksHasMassage(V2rayConstants.CORE_STATES tun2SocksState, String newMessage) {
        Log.i("TUN2SOCKS", newMessage);
    }

}
