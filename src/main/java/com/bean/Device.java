package com.bean;

import java.io.Serializable;

/**
 * @author Lvan
 */
public class Device implements Serializable {

	private static final long serialVersionUID = 5692837227581812121L;

	@SuppressWarnings("unused")
	private int id;
	private String hostMac;
	private String userId; // �û�id
	private String roomName;// ����id
	private String floorName;
	private String name; // ����
	private String equipmentMac;
	private String equipmentEp; // �߼��豸���
	private String devid; // �豸id ��� 0x0000, ���� 0x0001,����� 0x0002�����ſ��� 0x0003�� // // ����ιʳ 0x0015��PM2.5������ 0x8011�� // �¶ȴ�����
	private boolean online;  // ���ߣ�true or false
	private String amazonId;
	private String progressBar="0";
	private String userRelationId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	public Device() {
	}

	public Device(String hostMac, String name, String equipmentMac, String ep, String devid ) {
		this.hostMac = hostMac;
		this.name = name;
		this.equipmentMac = equipmentMac;
		this.equipmentEp = ep;
		this.devid = devid;
	}

	@Override
	public String toString() {
		return ("Device [computer_mac=" + hostMac + ", name=" + name + ", mac=" + equipmentMac + ", ep=" + equipmentEp + ", devid="
				+ devid+ ", roomName="+ roomName+ ", floorName="+ floorName + ", online="+ online + ", +hashcode="+  hashCode()+ ", +amazonId="+  amazonId+"progressBar="+progressBar );

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.hostMac.hashCode()+this.equipmentMac.hashCode()+this.equipmentEp.hashCode()+100;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub

		if (!(obj instanceof Device))
			throw new ClassCastException("���Ͳ�ƥ��");

		Device device = (Device) obj;
		if( device.getHostMac()==null  || device.getEquipmentMac()== null  || device.getEquipmentEp()==null){
			return false;
		}
		
		return  device.getHostMac().equals( this.getHostMac() )&& device.getEquipmentMac().equals(this.getEquipmentMac() ) && device.getEquipmentEp().equals( this.getEquipmentEp() );
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getEquipmentMac() {
		return equipmentMac;
	}

	public void setEquipmentMac(String equipmentMac) {
		this.equipmentMac = equipmentMac;
	}

	public boolean isOnline() {
		return online;
	}

	public String getUserRelationId() {
		return userRelationId;
	}

	public void setUserRelationId(String userRelationId) {
		this.userRelationId = userRelationId;
	}

	public int getId() {
		return id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquipmentEp() {
		return equipmentEp;
	}

	public void setEquipmentEp(String equipmentEp) {
		this.equipmentEp = equipmentEp;
	}

	public String getDevid() {
		return devid;
	}

	public void setDevid(String devid) {
		this.devid = devid;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getAmazonId() {
		return amazonId;
	}

	public void setAmazonId(String amazonId) {
		this.amazonId = amazonId;
	}

	public String getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}
}
