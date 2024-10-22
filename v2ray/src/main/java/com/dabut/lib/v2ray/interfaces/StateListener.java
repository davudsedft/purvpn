package com.dabut.lib.v2ray.interfaces;


import com.dabut.lib.v2ray.utils.V2rayConstants;

public interface StateListener {
    V2rayConstants.CONNECTION_STATES getConnectionState();

    V2rayConstants.CORE_STATES getCoreState();

    long getDownloadSpeed();

    long getUploadSpeed();



}
