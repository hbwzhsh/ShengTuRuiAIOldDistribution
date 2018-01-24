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
public class DevicesSync {

    private String id;
    private String type;
    private List<String> traits;
    private NameSync name;
    private boolean willReportState;
    private DeviceInfoSync deviceInfo;
    private CustomDataSync customData;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setTraits(List<String> traits) {
         this.traits = traits;
     }
     public List<String> getTraits() {
         return traits;
     }

    public void setName(NameSync name) {
         this.name = name;
     }
     public NameSync getName() {
         return name;
     }

    public void setWillReportState(boolean willReportState) {
         this.willReportState = willReportState;
     }
     public boolean getWillReportState() {
         return willReportState;
     }

    public void setDeviceInfo(DeviceInfoSync deviceInfo) {
         this.deviceInfo = deviceInfo;
     }
     public DeviceInfoSync getDeviceInfo() {
         return deviceInfo;
     }

    public void setCustomData(CustomDataSync customData) {
         this.customData = customData;
     }
     public CustomDataSync getCustomData() {
         return customData;
     }

}