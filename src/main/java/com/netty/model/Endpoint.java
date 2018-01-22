package com.netty.model;

import java.util.List;

public class Endpoint {

	private int devid;
	private List<String> cmdlist;
	private List<IdValue> attrlist;
	private int ep;
	private String mac;
	private int cost;
	private String roomId;
	private String deviceshortcode;
	private String customName;
	private int deviceRowId;

	public int getDeviceRowId() {
		return deviceRowId;
	}

	public void setDeviceRowId(int deviceRowId) {
		this.deviceRowId = deviceRowId;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getDeviceshortcode() {
		return deviceshortcode;
	}

	public void setDeviceshortcode(String deviceshortcode) {
		this.deviceshortcode = deviceshortcode;
	}


	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<String> getCmdlist() {
		return cmdlist;
	}
	public void setCmdlist(List<String> cmdlist) {
		this.cmdlist = cmdlist;
	}
	
	
	
	
	public List<IdValue> getAttrlist() {
		return attrlist;
	}
	public void setAttrlist(List<IdValue> attrlist) {
		this.attrlist = attrlist;
	}
	public int getDevid() {
		return devid;
	}
	public void setDevid(int devid) {
		this.devid = devid;
	}
	public int getEp() {
		return ep;
	}
	public void setEp(int ep) {
		this.ep = ep;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}

