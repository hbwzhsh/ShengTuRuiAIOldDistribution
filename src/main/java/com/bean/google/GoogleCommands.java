package com.bean.google;

import java.util.List;

public class GoogleCommands {

    private List<GoogleCMDDevices> devices;

    private List<GoogleExecutions> execution;

    public List<GoogleCMDDevices> getDevices() {
        return devices;
    }

    public void setDevices(List<GoogleCMDDevices> devices) {
        this.devices = devices;
    }

    public List<GoogleExecutions> getExecution() {
        return execution;
    }

    public void setExecution(List<GoogleExecutions> execution) {
        this.execution = execution;
    }
}
