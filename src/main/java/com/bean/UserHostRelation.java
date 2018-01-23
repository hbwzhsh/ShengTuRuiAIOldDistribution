package com.bean;

import java.util.Date;

/*
 * �̵���
 */
public class UserHostRelation {

	private String mac;
	private String ip;
	private String hostName;
	private String email;
	private String userId;
	private String amazonId;
	private Date createTime;
	private Date updateTime;
	private String devid;
	private String name;
	private String equipmentEp;
	private String equipmentMac;
	
	
	

	public String getEquipmentEp() {
		return equipmentEp;
	}

	public void setEquipmentEp(String equipmentEp) {
		this.equipmentEp = equipmentEp;
	}

	public String getEquipmentMac() {
		return equipmentMac;
	}

	public void setEquipmentMac(String equipmentMac) {
		this.equipmentMac = equipmentMac;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmazonId() {
		return amazonId;
	}

	public void setAmazonId(String amazonId) {
		this.amazonId = amazonId;
	}


	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
