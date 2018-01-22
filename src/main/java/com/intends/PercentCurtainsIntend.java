package com.intends;

import com.init.Constants;
import com.init.DeviceService;
import com.netty.model.Device;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PercentCurtainsIntend extends LightAndCurtainsAbstract {

	private DeviceService deviceService = new DeviceService();


	@Override
	void sendCmdToServer(List<Device> filterlist) {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getItem().getPersentage())) {
			deviceService.sendCmdToServer(filterlist, Constants.percentcurtain, getItem());
		} else {
			if (Constants.OPENCURTAINALITTLE.equalsIgnoreCase(getItem().getIntentName())) {
				deviceService.sendCmdToServerForOpenAlittle(filterlist, Constants.percentcurtain, true,getItem());
			} else if (Constants.CLOSECURTAINALITTLE.equalsIgnoreCase(getItem().getIntentName())) {
				deviceService.sendCmdToServerForOpenAlittle(filterlist, Constants.percentcurtain, false,getItem());
			} else if ( Constants.OPENTHECURTAINHALFWAY.equalsIgnoreCase(getItem().getIntentName()) ) {
				getItem().setOp(50);
				deviceService.sendCmdToServer(filterlist, Constants.percentcurtain, getItem());
			}
		}
	}
	@Override
	List<Device> findDevices() {
		// TODO Auto-generated method stub
		return deviceService.filterDataByIntentName(getItem());
	}
}
