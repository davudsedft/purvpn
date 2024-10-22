package com.dabut.lib.v2ray.model;

import java.io.Serializable;
import java.util.ArrayList;

public class V2rayConfigModel implements Serializable {

    public String applicationName;
    public int applicationIcon;
    public String remark;
    public ArrayList<String> blockedApplications = null;
    public String fullJsonConfig;
    public String currentServerAddress = "";
    public int currentServerPort = 443;
    public int localSocksPort = 10808;
    public int localHttpPort = 10809;
    public int localDNSPort = 1053;
    public boolean enableTrafficStatics = true;
    public boolean enableTrafficStaticsOnNotification = true;
    public boolean enableLocalTunneledDNS = false;

}
