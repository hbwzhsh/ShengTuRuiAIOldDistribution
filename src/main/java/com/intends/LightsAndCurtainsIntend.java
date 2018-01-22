package com.intends;

import com.init.DeviceService;
import com.netty.model.Device;

import java.util.List;


public class LightsAndCurtainsIntend extends LightAndCurtainsAbstract {

	private DeviceService deviceService = new DeviceService();


	@Override
	void sendCmdToServer(List<Device> filterlist) {
		// TODO Auto-generated method stub
		deviceService.sendCmdToServer(filterlist, getItem().getDeviceCMD(), getItem());
	}

	@Override
	List<Device> findDevices() {
		// TODO Auto-generated method stub
		return deviceService.filterDataByIntentName(getItem());
	}

}
