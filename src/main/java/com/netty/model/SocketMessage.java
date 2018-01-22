package com.netty.model;

public class SocketMessage {

	private String type;
	private String hostMac;
	private String body;
	
	
	
	
	public String getHostMac() {
		return hostMac;
	}
	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
