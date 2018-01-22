package com.netty.model;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;


public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private int id;
    private int userId;
    private String customName;
    private String deviceName;
    private String deviceMac;
    private String hostMac;
    private String software_version;
    private String hardware_version;
    private String endpoint;
    private int deviceType;
    private String roomId;
    private String deviceNumber;
    private String deviceshortcode;
    private int ep;
    private int devid;
    private List<String> cmdlist;
    private List<IdValue> attrlist;

    private boolean isonline;
    private int cost;

    private String deviceCmds;
    private String deviceAttrs;
    private String roomName;


    public Device(){

    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Device(int ep){
        this.ep   =  ep;
    }


    public String getDeviceshortcode() {
        return deviceshortcode;
    }

    public void setDeviceshortcode(String deviceshortcode) {
        this.deviceshortcode = deviceshortcode;
    }


    public String getDeviceCmds() {
        return deviceCmds;
    }

    public void setDeviceCmds(String deviceCmds) {

        try {
            this.deviceCmds = deviceCmds;
            if (StringUtils.isNotBlank(deviceCmds)) {
                this.cmdlist = JSONArray.parseArray(deviceCmds, String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDeviceAttrs() {
        return deviceAttrs;
    }

    public void setDeviceAttrs(String deviceAttrs) {
        try {
            this.deviceAttrs = deviceAttrs;

            if (StringUtils.isNotBlank(deviceAttrs)) {
                this.attrlist = JSONArray.parseArray(deviceAttrs, IdValue.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public List<String> getCmdlist() {
        return cmdlist;
    }

    public void setCmdlist(List<String> cmdlist) {
        this.deviceCmds = JSONArray.toJSONString(cmdlist);
        this.cmdlist = cmdlist;
    }

    public List<IdValue> getAttrlist() {
        return attrlist;
    }

    public int getProgressBar(){
        for(IdValue idValue:this.attrlist){
            if( idValue.getId() == 4 ){
                return idValue.getValue();
            }
        }
        return 0;
    }

    public void setAttrlist(List<IdValue> attrlist) {
        this.deviceAttrs = JSONArray.toJSONString(attrlist);
        this.attrlist = attrlist;
    }

    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isIsonline() {
        return isonline;
    }

    public void setIsonline(boolean isonline) {
        this.isonline = isonline;
    }


    public int getEp() {
        return ep;
    }

    public void setEp(int ep) {
        this.ep = ep;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getHostMac() {
        return hostMac;
    }

    public void setHostMac(String hostMac) {
        this.hostMac = hostMac;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public String getSoftware_version() {
        return software_version;
    }

    public void setSoftware_version(String software_version) {
        this.software_version = software_version;
    }

    public String getHardware_version() {
        return hardware_version;
    }

    public void setHardware_version(String hardware_version) {
        this.hardware_version = hardware_version;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }


    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

}



