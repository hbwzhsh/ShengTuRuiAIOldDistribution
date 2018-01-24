package com.bean.google.newpojo.sync; /**
  * Copyright 2018 bejson.com 
  */
import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:27:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PayloadSync {

    private String agentUserId;
    private List<DevicesSync> devices;
    public void setAgentUserId(String agentUserId) {
         this.agentUserId = agentUserId;
     }
     public String getAgentUserId() {
         return agentUserId;
     }

    public void setDevices(List<DevicesSync> devices) {
         this.devices = devices;
     }
     public List<DevicesSync> getDevices() {
         return devices;
     }

}