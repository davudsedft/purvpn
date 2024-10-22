package com.dabut.lib.v2ray.utils;

public class V2rayConstants {
    public static final String V2RAY_SERVICE_OPENED_APPLICATION_INTENT = "APP_OPEN_FROM_V2RAY_NOTIFICATION_INTENT";
    public static final String V2RAY_SERVICE_STATICS_BROADCAST_INTENT = "V2RAY_SERVICE_STATICS_INTENT";
    public static final String V2RAY_SERVICE_COMMAND_INTENT = "V2RAY_SERVICE_COMMAND_INTENT";
    public static final String V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_INTENT = "V2RAY_SERVICE_CURRENT_CONFIG_DELAY_INTENT";
    public static final String V2RAY_SERVICE_CURRENT_CONFIG_DELAY_BROADCAST_EXTRA = "V2RAY_SERVICE_CURRENT_CONFIG_DELAY_EXTRA";
    public static final String V2RAY_SERVICE_COMMAND_EXTRA = "V2RAY_SERVICE_COMMAND_EXTRA";
    public static final String V2RAY_SERVICE_CONFIG_EXTRA = "V2RAY_SERVICE_CONFIG_EXTRA";
    public static final String SERVICE_CONNECTION_STATE_BROADCAST_EXTRA = "CONNECTION_STATE_EXTRA";
    public static final String SERVICE_TYPE_BROADCAST_EXTRA = "SERVICE_TYPE_EXTRA";
    public static final String SERVICE_CORE_STATE_BROADCAST_EXTRA = "CORE_STATE_EXTRA";
    public static final String SERVICE_DURATION_BROADCAST_EXTRA = "SERVICE_DURATION_EXTRA";
    public static final String SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA = "UPLOAD_SPEED_EXTRA";
    public static final String SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA = "DOWNLOAD_SPEED_EXTRA";
    public static final String SERVICE_DOWNLOAD_TRAFFIC_BROADCAST_EXTRA = "DOWNLOADED_TRAFFIC_EXTRA";
    public static final String SERVICE_UPLOAD_TRAFFIC_BROADCAST_EXTRA = "UPLOADED_TRAFFIC_EXTRA";
    public static final long BYTE = 1;
    public static final long KILO_BYTE = BYTE * 1024;
    public static final long MEGA_BYTE = KILO_BYTE * 1024;
    public static final long GIGA_BYTE = MEGA_BYTE * 1024;

    public enum SERVICE_MODES {
        VPN_MODE,
        PROXY_MODE
    }

    public enum SERVICE_COMMANDS {
        START_SERVICE,
        STOP_SERVICE,
        MEASURE_DELAY
    }

    public enum CONNECTION_STATES {
        CONNECTED,
        CONNECTING,
        DISCONNECTED,
    }

    public enum CORE_STATES {
        RUNNING,
        IDLE,
        STOPPED,
    }
    public static final String DEFAULT_OUT_BOUND_PLACE_IN_FULL_JSON_CONFIG = "CONFIG_PROXY_OUTBOUND_PLACE";
    public static final String DEFAULT_FULL_JSON_CONFIG = "{\n" +
            "  \"dns\": {\n" +
            "    \"hosts\": {\n" +
            "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
            "    },\n" +
            "    \"servers\": [\n" +
            "      \"1.1.1.1\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"inbounds\": [\n" +
            "    {\n" +
            "      \"listen\": \"127.0.0.1\",\n" +
            "      \"port\": 10808,\n" +
            "      \"protocol\": \"socks\",\n" +
            "      \"settings\": {\n" +
            "        \"auth\": \"noauth\",\n" +
            "        \"udp\": true,\n" +
            "        \"userLevel\": 8\n" +
            "      },\n" +
            "      \"sniffing\": {\n" +
            "        \"destOverride\": [],\n" +
            "        \"enabled\": false\n" +
            "      },\n" +
            "      \"tag\": \"socks\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"listen\": \"127.0.0.1\",\n" +
            "      \"port\": 10809,\n" +
            "      \"protocol\": \"http\",\n" +
            "      \"settings\": {\n" +
            "        \"userLevel\": 8\n" +
            "      },\n" +
            "      \"tag\": \"http\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"log\": {\n" +
            "    \"loglevel\": \"error\"\n" +
            "  },\n" +
            "  \"outbounds\": [\n" +
            "    "+DEFAULT_OUT_BOUND_PLACE_IN_FULL_JSON_CONFIG+",\n" +
            "    {\n" +
            "      \"protocol\": \"freedom\",\n" +
            "      \"settings\": {},\n" +
            "      \"tag\": \"direct\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"protocol\": \"blackhole\",\n" +
            "      \"settings\": {\n" +
            "        \"response\": {\n" +
            "          \"type\": \"http\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"tag\": \"block\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"remarks\": \"test\",\n" +
            "  \"routing\": {\n" +
            "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
            "    \"rules\": [\n" +
            "      {\n" +
            "        \"ip\": [\n" +
            "          \"1.1.1.1\"\n" +
            "        ],\n" +
            "        \"outboundTag\": \"proxy\",\n" +
            "        \"port\": \"53\",\n" +
            "        \"type\": \"field\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
}
