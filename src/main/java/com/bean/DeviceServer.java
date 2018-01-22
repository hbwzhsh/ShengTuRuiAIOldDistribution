package com.bean;

import java.io.Serializable;

/**
 * 
 * @author weiTaZhuang
 * @date 2016��5��10�� ����9:50:42
 * @Description ������
 */

public class DeviceServer implements Serializable {

	private static final long serialVersionUID = -5268461222959781669L;

	private int id;
	private int user_id;
	private String name; // ������
	private String mac; // mac��ַ
	private String ip; // ip��ַ
	private int port; // port

	private String url;

	/** �Ƿ����� */
	private boolean online = false;

	public DeviceServer() {
		super();
	}

	public DeviceServer(String mac, String name) {
		super();
		this.mac = mac;
		this.name = name;

	}

	public DeviceServer(String mac, String ip, int port, String email) {
		super();
		this.mac = mac;
		this.ip = ip;
		this.port = port;

	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	// ���������ʾ��PickerView������ַ���,PickerView��ͨ�������ȡgetPickerViewText������ʾ������
	public String getPickerViewText() {
		// ���ﻹ�����ж����ֳ����ض����ṩ��ʾ
		return name;
	}

}
