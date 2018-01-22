package com.model;

import java.io.Serializable;
import java.util.List;

public class Floor implements Serializable {
	private static final long serialVersionUID = 8915993147726490623L;
	private Integer floorId;
	private String floorName;
	
	List<House> roomlist;

	public List<House> getRoomlist() {
		return roomlist;
	}

	public void setRoomlist(List<House> roomlist) {
		this.roomlist = roomlist;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}


	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}


}
