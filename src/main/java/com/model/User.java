package com.model;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User {

	// Fields
	private String userId;
	private String userName;// 账号
	private String password;// 密码
	private String mac;//
	private int status;// 状态（1：启用；0：停用）
	private String createTime;// 创建时间
	private String updateTime;// 修改时间
	private String phone;// 手机号
	private String endTime;// 结束时间
	private String validateCode;// 激活码
	private String validateState;// 激活状态
	private String email;//邮箱
	private String uuid; //邮箱
	private String uuidStatus;//邮箱
	private String token; //token
	private int parentId;
	private String domain;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}







	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getValidateState() {
		return validateState;
	}

	public void setValidateState(String validateState) {
		this.validateState = validateState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuidStatus() {
		return uuidStatus;
	}

	public void setUuidStatus(String uuidStatus) {
		this.uuidStatus = uuidStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}