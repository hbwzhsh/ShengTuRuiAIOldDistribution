package com.netty.model;

import java.util.List;

public class SocketMessageFormatDescriptionResponse {
	
	private String type;
	private int transid;
	private String from;
	private Object body;
	private String userid;
	private int dataversion;


	public int getDataversion() {
		return dataversion;
	}

	public void setDataversion(int dataversion) {
		this.dataversion = dataversion;
	}

	public void setBody(List<Object> body) {
		this.body = body;
	}



	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public int getTransid() {
		return transid;
	}
	public void setTransid(int transid) {
		this.transid = transid;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object obj) {

		this.body = obj;
	}
	
	
	
}


