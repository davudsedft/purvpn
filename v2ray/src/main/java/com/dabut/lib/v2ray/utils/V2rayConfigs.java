package com.dabut.lib.v2ray.utils;


import com.dabut.lib.v2ray.model.V2rayConfigModel;

public class V2rayConfigs {
    public static V2rayConstants.CONNECTION_STATES connectionState = V2rayConstants.CONNECTION_STATES.DISCONNECTED;
    public static V2rayConstants.SERVICE_MODES serviceMode = V2rayConstants.SERVICE_MODES.VPN_MODE;
    public static V2rayConfigModel currentConfig = new V2rayConfigModel();
}
