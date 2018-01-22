package com.intends;

import com.init.DeviceService;
import com.netty.model.Device;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class DurationCurtainsIntend extends LightAndCurtainsAbstract {

	DeviceService deviceService  = new DeviceService();

	private final static String timerTypeForMinutes = "AmazonMinutes";
	private final static String timerTypeForHours = "AmazonHours";

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

	private void setTimerForCMD() {

		String timer = (getIntentSession().getSlot("timer") == null) ? StringUtils.EMPTY : getIntentSession().getSlot("timer").getValue();
		String timerType = (getIntentSession().getSlot("timerType") == null) ? StringUtils.EMPTY : getIntentSession().getSlot("timerType").getValue();
		String actionType = (getIntentSession().getSlot("actionType") == null) ? StringUtils.EMPTY : getIntentSession().getSlot("actionType").getValue();

		int tempMinuts = 0;
		if (timerTypeForHours.equalsIgnoreCase(timerType) && StringUtils.isNumeric(timer)) {
			tempMinuts = Integer.parseInt(timer) * 10;
		}

		Timer dotimer = new Timer();
		dotimer.schedule(new TimerTask() {
			@Override
			public void run() {
				//deviceService.sendCmdToServer(findDevices(), getItem().getDeviceCMD(), getItem());
			}

		}, 1000 * 60 * tempMinuts);
	}

}
