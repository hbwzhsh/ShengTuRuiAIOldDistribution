package com.bean;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class User {

	private String userId;
	private String userName;
	private String amazonId;
	private String password;
	private String email;
	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAmazonId() {
		return amazonId;
	}

	public void setAmazonId(String amazonId) {
		this.amazonId = amazonId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}