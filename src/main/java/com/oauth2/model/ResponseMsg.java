package com.oauth2.model;

import java.io.Serializable;

public class ResponseMsg implements Serializable {

	private String status;  //  2 正确
	private Object data;

	public final static String success="success";

	public final static String failed="failed";
	public final static String relogin="relogin";

	public void setFailedStatus(){
		this.setStatus(failed);
	}

	public void setSuccessStatus(){
		this.setStatus(success);
	}

	public ResponseMsg() {
		this.status = failed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
