package com.intent.amazonintent.refacting.intends;

import com.bean.Device;
import com.intent.amazonintent.DeviceService;

import java.util.List;

public class LightsAndCurtainsIntend extends LightAndCurtainsAbstract  {

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
