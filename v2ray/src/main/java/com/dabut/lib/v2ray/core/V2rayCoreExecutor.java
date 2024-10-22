package com.dabut.lib.v2ray.core;

import static com.dabut.lib.v2ray.utils.Utilities.getDeviceIdForXUDPBaseKey;
import static com.dabut.lib.v2ray.utils.Utilities.getUserAssetsPath;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA;
import static com.dabut.lib.v2ray.utils.V2rayConstants.V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.dabut.lib.v2ray.interfaces.V2rayServicesListener;
import com.dabut.lib.v2ray.model.V2rayConfigModel;
import com.dabut.lib.v2ray.utils.Utilities;
import com.dabut.lib.v2ray.utils.V2rayConstants;

import org.json.JSONObject;

import go.Seq;
import libv2ray.Libv2ray;
import libv2ray.PurlitePoint;
import libv2ray.PurliteVPNServiceSupportsSet;

public class V2rayCoreExecutor {

    private V2rayConstants.CORE_STATES coreState;
    public V2rayServicesListener v2rayServicesListener;


    public final PurlitePoint v2RayPoint = Libv2ray.newV2RayPoint(new PurliteVPNServiceSupportsSet() {
        @Override
        public long shutdown() {
            try {
                if (v2rayServicesListener == null) {
                    Log.d(V2rayCoreExecutor.class.getSimpleName(), "shutdown => can`t find initialed service.");
                    return -1;
                }
                v2rayServicesListener.stopService();
                v2rayServicesListener = null;
                return 0;
            } catch (Exception e) {
                Log.d(V2rayCoreExecutor.class.getSimpleName(), "shutdown =>", e);
                return -1;
            }
        }

        @Override
        public long prepare() {
            return 0;
        }

        @Override
        public boolean protect(long l) {
            if (v2rayServicesListener != null)
                return v2rayServicesListener.onProtect((int) l);
            return true;
        }

        @Override
        public long onEmitStatus(long l, String s) {
            return 0;
        }

        @Override
        public long setup(String s) {
            if (v2rayServicesListener != null) {
                try {
                    coreState = V2rayConstants.CORE_STATES.RUNNING;
                    v2rayServicesListener.startService();
                } catch (Exception e) {
                    Log.d(V2rayCoreExecutor.class.getSimpleName(), "setupFailed => ", e);
                    return -1;
                }
            }
            return 0;
        }
    }, Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1);


    public V2rayCoreExecutor(final Service targetService) {
        this.v2rayServicesListener = (V2rayServicesListener) targetService;
        Seq.setContext(targetService);
        Libv2ray.initV2Env(getUserAssetsPath(targetService.getApplicationContext()), getDeviceIdForXUDPBaseKey());
        coreState = V2rayConstants.CORE_STATES.IDLE;
        Log.d(V2rayCoreExecutor.class.getSimpleName(), "V2rayCoreExecutor -> New initialize from : " + targetService.getClass().getSimpleName());
    }

    public void startCore(final V2rayConfigModel v2rayConfig) {
        try {
            stopCore(false);
            try {
                Libv2ray.testConfig(v2rayConfig.fullJsonConfig);
            } catch (Exception testException) {
                coreState = V2rayConstants.CORE_STATES.STOPPED;
                Log.d(V2rayCoreExecutor.class.getSimpleName(), "startCore => v2ray json config not valid.", testException);
                stopCore(true);
                return;
            }
            v2RayPoint.setConfigureFileContent(v2rayConfig.fullJsonConfig);
            v2RayPoint.setDomainName(Utilities.normalizeIpv6(v2rayConfig.currentServerAddress) + ":" + v2rayConfig.currentServerPort);
            v2RayPoint.runLoop(false);
        } catch (Exception e) {
            Log.e(V2rayCoreExecutor.class.getSimpleName(), "startCore =>", e);
        }
    }

    public void stopCore(final boolean shouldStopService) {
        try {
            if (v2RayPoint.getIsRunning()) {
                v2RayPoint.stopLoop();
                if (shouldStopService) {
                    v2rayServicesListener.stopService();
                }
                coreState = V2rayConstants.CORE_STATES.STOPPED;
            }
        } catch (Exception e) {
            Log.d(V2rayCoreExecutor.class.getSimpleName(), "stopCore =>", e);
        }
    }

    public long getDownloadSpeed() {
        return v2RayPoint.queryStats("block", "downlink") + v2RayPoint.queryStats("proxy", "downlink");
    }

    public long getUploadSpeed() {
        return v2RayPoint.queryStats("block", "uplink")  + v2RayPoint.queryStats("proxy", "uplink");
    }

    public V2rayConstants.CORE_STATES getCoreState() {
        if (coreState == V2rayConstants.CORE_STATES.RUNNING) {
            if (!v2RayPoint.getIsRunning()) {
                coreState = V2rayConstants.CORE_STATES.STOPPED;
            }
            return coreState;
        }
        return coreState;
    }

    public void broadCastCurrentServerDelay() {
        try {
            if (v2rayServicesListener != null) {
                int serverDelay = (int) v2RayPoint.measureDelay("");
                Intent serverDelayBroadcast = new Intent(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT);
                serverDelayBroadcast.setPackage(v2rayServicesListener.getService().getPackageName());
                serverDelayBroadcast.putExtra(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA, serverDelay);
                v2rayServicesListener.getService().sendBroadcast(serverDelayBroadcast);
            }
        } catch (Exception e) {
            Log.d(V2rayCoreExecutor.class.getSimpleName(), "broadCastCurrentServerDelay => ", e);
            Intent serverDelayBroadcast = new Intent(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT);
            serverDelayBroadcast.setPackage(v2rayServicesListener.getService().getPackageName());
            serverDelayBroadcast.putExtra(V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA, -1);
            v2rayServicesListener.getService().sendBroadcast(serverDelayBroadcast);
        }
    }

    public static long getConfigDelay(final String config) {
        try {
            JSONObject config_json = new JSONObject(config);
            config_json.remove("routing");
            config_json.remove("dns");
            JSONObject routing = new JSONObject();
            routing.put("domainStrategy", "IPIfNonMatch");
            config_json.put("routing", routing);
            config_json.put("dns", new JSONObject("{\n" +
                    "    \"hosts\": {\n" +
                    "        \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                    "    },\n" +
                    "    \"servers\": [\n" +
                    "        \"1.1.1.1\"\n" +
                    "    ]\n" +
                    "}"));
            return Libv2ray.purliteOutboundDelay(config_json.toString(), "");
        } catch (Exception json_error) {
            Log.d(V2rayCoreExecutor.class.getSimpleName(), "getCurrentServerDelay -> ", json_error);
            return -1;
        }
    }

}
