package com.intends.util;

import com.init.Constants;
import com.model.IntendType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DeviceTypeFactory {
	private static List<IntendType> deviceTypeList = new ArrayList<IntendType>();

	public static List<IntendType> getDeviceTypeList() {
		return deviceTypeList;
	}

	static {

		List<String> lightsList = Arrays.asList("1", "2","3");
		List<String> curtainsList = Arrays.asList("0");

		deviceTypeList.add(new IntendType(Constants.TURNONLIGHT, lightsList, Constants.openCmd));
		deviceTypeList.add(new IntendType(Constants.TURNOFFLIGHT, lightsList, Constants.closeCmd));
		deviceTypeList.add(new IntendType(Constants.CLOSECURTAINS, curtainsList, Constants.closeCmd));
		deviceTypeList.add(new IntendType(Constants.OPENCURTAINS, curtainsList, Constants.openCmd));
		deviceTypeList.add(new IntendType(Constants.OPENCURTAINALITTLE, curtainsList, Constants.openCmd));
		deviceTypeList.add(new IntendType(Constants.CLOSECURTAINALITTLE, curtainsList, Constants.openCmd));
		deviceTypeList.add(new IntendType(Constants.DIMLIGHT, lightsList, Constants.dimlightCmd));
		deviceTypeList.add(new IntendType(Constants.OPENTHECURTAINHALFWAY, curtainsList, Constants.openCmd));

	}

	public static IntendType getDeviceByIntendName(String intendName) {
		return getDeviceTypeList().stream().filter(item -> item.getIntendName().equalsIgnoreCase(intendName)).findAny().orElse(null);
	}

}
