package com.bean.google.newpojo.command.request; /**
  * Copyright 2018 bejson.com 
  */
import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:37:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Commands {

    private List<Devices> devices;
    private List<Execution> execution;
    public void setDevices(List<Devices> devices) {
         this.devices = devices;
     }
     public List<Devices> getDevices() {
         return devices;
     }

    public void setExecution(List<Execution> execution) {
         this.execution = execution;
     }
     public List<Execution> getExecution() {
         return execution;
     }

}