package com.netty.model;

import java.util.List;

public class DeviceDescription {

	private String mac;
	private boolean isonline;
	private String partcode;
	private String hardware_ver;
	private String softver_ver;
	private List<Endpoint> endpoint;
	private boolean iswareless;
	private String deviceshortcode;


	public String getDeviceshortcode() {
		return deviceshortcode;
	}

	public void setDeviceshortcode(String deviceshortcode) {
		this.deviceshortcode = deviceshortcode;
	}

	public boolean isIswareless() {
		return iswareless;
	}
	public void setIswareless(boolean iswareless) {
		this.iswareless = iswareless;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}


	public boolean isIsonline() {
		return isonline;
	}
	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}
	public String getPartcode() {
		return partcode;
	}
	public void setPartcode(String partcode) {
		this.partcode = partcode;
	}
	public String getHardware_ver() {
		return hardware_ver;
	}
	public void setHardware_ver(String hardware_ver) {
		this.hardware_ver = hardware_ver;
	}
	public String getSoftver_ver() {
		return softver_ver;
	}
	public void setSoftver_ver(String softver_ver) {
		this.softver_ver = softver_ver;
	}
	public List<Endpoint> getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(List<Endpoint> endpoint) {
		this.endpoint = endpoint;
	}
	
	
	
	
	
	
}
