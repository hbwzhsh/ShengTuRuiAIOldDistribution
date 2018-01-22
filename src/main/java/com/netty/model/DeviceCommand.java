package com.netty.model;

import java.util.List;

public class DeviceCommand {

	private String mac;
	private int transid;
	private int ep;
	private int devid;
	private int cmdid;
	private List<String> op;

	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}
	public int getEp() {
		return ep;
	}
	public void setEp(int ep) {
		this.ep = ep;
	}
	public int getDevid() {
		return devid;
	}
	public void setDevid(int devid) {
		this.devid = devid;
	}
	public int getCmdid() {
		return cmdid;
	}
	public void setCmdid(int cmdid) {
		this.cmdid = cmdid;
	}

	public List<String> getOp() {
		return op;
	}

	public void setOp(List<String> op) {
		this.op = op;
	}
}
