package com.bean.taobao;

public class AliPayload {

    private String accessToken;
    private String deviceId;
    private String deviceType;
    private String attribute;
    private String value;
    private AliExtensions extensions;

    public AliExtensions getExtensions() {
        return extensions;
    }

    public void setExtensions(AliExtensions extensions) {
        this.extensions = extensions;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
