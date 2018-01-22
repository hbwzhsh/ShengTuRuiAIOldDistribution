package com.intent.amazonintent.refacting.intends;

import com.bean.Device;
import com.init.Constants;
import com.init.ConstantsMethod;
import com.intent.amazonintent.DeviceService;
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
				deviceService.sendCmdToServerForOpenAlittle(filterlist, Constants.percentcurtain, true);
			} else if (Constants.CLOSECURTAINALITTLE.equalsIgnoreCase(getItem().getIntentName())) {
				deviceService.sendCmdToServerForOpenAlittle(filterlist, Constants.percentcurtain, false);
			} else if (Constants.OPENTHECURTAINHALFWAY.equalsIgnoreCase(getItem().getIntentName())) {
				getItem().setPersentage("50");
				getItem().setDeviceState(ConstantsMethod.getProcessBarCmd("50"));
				
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
