package com.intent.amazonintent.refacting;

import com.bean.IntendType;
import com.utility.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceTypeFactory {
	private static List<IntendType> deviceTypeList = new ArrayList<IntendType>();

	public static List<IntendType> getDeviceTypeList() {
		return deviceTypeList;
	}

	static {

		List<String> lightsList = Arrays.asList("0001", "0002","0003");
		List<String> curtainsList = Arrays.asList("0000");

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
