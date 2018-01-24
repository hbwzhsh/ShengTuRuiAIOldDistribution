package com.bean;

import java.io.Serializable;

/**
 * @author Lvan
 */
public class Device implements Serializable {

	private static final long serialVersionUID = 5692837227581812121L;

	@SuppressWarnings("unused")
	private String id;
	private String hostMac;
	private String userId; // �û�id
	private String roomName;// ����id
	private String floorName;
	private String name; // ����
	private String deviceMac; // �豸��mac��ַ key
	private String equipmentEp; // �߼��豸���
	private String devid; // �豸id ��� 0x0000, ���� 0x0001,����� 0x0002�����ſ��� 0x0003�� // // ����ιʳ 0x0015��PM2.5������ 0x8011�� // �¶ȴ�����
							// 0x8004��ʪ�ȴ����� //
							// 0x8005�����ٴ�����0x8007���״�������0x8006��ͨ��ң����0x000f
	private boolean online;  // ���ߣ�true or false
	private String amazonId;
	private String progressBar="0";

	public Device() {
	}

	public Device(String hostMac, String name, String mac, String ep, String devid ) {
		this.hostMac = hostMac;
		this.name = name;
		this.deviceMac = mac;
		this.equipmentEp = ep;
		this.devid = devid;
	}

	@Override
	public String toString() {
		return ("Device [computer_mac=" + hostMac + ", name=" + name + ", mac=" + deviceMac + ", ep=" + equipmentEp + ", devid="
				+ devid+ ", roomName="+ roomName+ ", floorName="+ floorName + ", online="+ online + ", +hashcode="+  hashCode()+ ", +amazonId="+  amazonId+"progressBar="+progressBar );

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.hostMac.hashCode()+this.deviceMac.hashCode()+this.equipmentEp.hashCode()+100;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if (!(obj instanceof Device))
			throw new ClassCastException("���Ͳ�ƥ��");

		Device device = (Device) obj;
		if( device.getHostMac()==null  || device.getDeviceMac()== null  || device.getEquipmentEp()==null){
			return false;
		}
		
		return  device.getHostMac().equals( this.getHostMac() )&& device.getDeviceMac().equals(this.getDeviceMac() ) && device.getEquipmentEp().equals( this.getEquipmentEp() );
	}
	

	public String getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public boolean getOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	
	public String getId() {
		return this.amazonId+"_"+this.hostMac+"_"+this.deviceMac+this.equipmentEp;
	}
	
	

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public String getHostMac() {
		return hostMac;
	}

	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEquipmentEp() {
		return equipmentEp;
	}

	public void setEquipmentEp(String equipmentEp) {
		this.equipmentEp = equipmentEp;
	}

	public String getAmazonId() {
		return amazonId;
	}

	public void setAmazonId(String amazonId) {
		this.amazonId = amazonId;
	}
	
	
    public static void main(String[] args) {

    	
	}
	

	

}
