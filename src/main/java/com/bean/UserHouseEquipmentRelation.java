package com.bean;

import java.io.Serializable;

public class UserHouseEquipmentRelation implements Serializable {

	/**
	 * 用户房间设备关联实体类
	 */
	private static final long serialVersionUID = -1794177619418318592L;
	private Integer userRelationId;
	private Integer userId;
	private String hostMac;
	private Integer houseId;
	private String floorAndHouseName;
	private String equipmentMac;
	private String equipmentEp;

	// private Date createTime;
	// private Date updateTime;

	public Integer getUserRelationId() {
		return userRelationId;
	}

	public void setUserRelationId(Integer userRelationId) {
		this.userRelationId = userRelationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// public Date getCreateTime() {
	// return createTime;
	// }
	//
	// public void setCreateTime(Date createTime) {
	// this.createTime = createTime;
	// }
	//
	// public Date getUpdateTime() {
	// return updateTime;
	// }
	//
	// public void setUpdateTime(Date updateTime) {
	// this.updateTime = updateTime;
	// }

	public String getHostMac() {
		return hostMac;
	}

	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}

	public String getEquipmentMac() {
		return equipmentMac;
	}

	public void setEquipmentMac(String equipmentMac) {
		this.equipmentMac = equipmentMac;
	}

	public String getEquipmentEp() {
		return equipmentEp;
	}

	public void setEquipmentEp(String equipmentEp) {
		this.equipmentEp = equipmentEp;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getFloorAndHouseName() {
		return floorAndHouseName;
	}

	public void setFloorAndHouseName(String floorAndHouseName) {
		this.floorAndHouseName = floorAndHouseName;
	}

}
