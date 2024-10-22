package com.dabut.lib.v2ray.interfaces;

import android.content.Context;

public interface TrafficListener {
    void onTrafficChanged(long uploadSpeed, long downloadSpeed, long uploadedTraffic, long downloadedTraffic , Context context);
}
