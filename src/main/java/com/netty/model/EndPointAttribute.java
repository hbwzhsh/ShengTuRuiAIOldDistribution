package com.netty.model;

import java.util.List;

public class EndPointAttribute {

	private String mac;
	private int devid;
	
	private List<IdValue> attr;
	private int ep;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getDevid() {
		return devid;
	}
	public void setDevid(int devid) {
		this.devid = devid;
	}
	
	
	
	
	
	public List<IdValue> getAttr() {
		return attr;
	}
	public void setAttr(List<IdValue> attr) {
		this.attr = attr;
	}
	public int getEp() {
		return ep;
	}
	public void setEp(int ep) {
		this.ep = ep;
	}
	
	

	
}
