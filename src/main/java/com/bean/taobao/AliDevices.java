package com.bean.taobao;

import java.util.Arrays;
import java.util.List;

public class AliDevices {

    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String brand;
    private String model;
    private String icon;
    private Object properties;
    private List<String> actions = Arrays.asList("TurnOn","TurnOff","SetBrightness","AdjustBrightness");
    private AliExtensions extensions;

    public AliExtensions getExtensions() {
        return extensions;
    }

    public void setExtensions(AliExtensions extensions) {
        this.extensions = extensions;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}
