package com.bean;

import com.amazon.speech.slu.Intent;
import com.utility.Constants;
import com.utility.ConstantsMethod;
import com.data.DeviceTypeFactory;
import org.apache.commons.lang3.StringUtils;

public class IntendParams {

	private String where;
	private String devicename;
	private String persentage;
	private String accessToken;
	private String intentName;
	private String deviceFuturePosition;
	private String deviceCMD;
	private String userId;


	


	public IntendParams() {
	}

	public IntendParams(String where, String devicename, String persentage, String accessToken, String intentName ) {
		super();
		this.where = where;
		this.devicename = devicename;
		this.persentage = persentage;
		this.accessToken = accessToken;
		this.intentName = intentName;

		this.deviceCMD = getCmdByIntentName(intentName, persentage);
		this.deviceFuturePosition = ConstantsMethod.getProcessBarCmd(persentage);
	}

	public static IntendParams createIntendParamsObj(Intent intent, String accessToken) {
		String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
		String where = (intent.getSlot("where") == null) ? StringUtils.EMPTY : intent.getSlot("where").getValue();
		String deviceName = (intent.getSlot("devicename") == null) ? StringUtils.EMPTY : intent.getSlot("devicename").getValue();
		String persentage = (intent.getSlot("persentage") == null) ? StringUtils.EMPTY : intent.getSlot("persentage").getValue();

		if (StringUtils.isBlank(where)) {
			where = Constants.defualtRooms.get(accessToken);
		}
		
		if(StringUtils.isBlank(persentage)) {
			persentage = getPercentByIntentName(intentName);
		}

		return new IntendParams(where, deviceName, persentage, accessToken, intentName );
	}

	private String getCmdByIntentName(String intendName, String percent) {
		IntendType deviceType = DeviceTypeFactory.getDeviceByIntendName(intendName);
		return deviceType.getCmd();
	}
	
	
	private static String getPercentByIntentName(String intendName) {
		
		if(intendName.equalsIgnoreCase(Constants.TURNONLIGHT)|| intendName.equalsIgnoreCase(Constants.OPENCURTAINS)) {
			return Constants.maxPercent;
		}else if(intendName.equalsIgnoreCase(Constants.TURNOFFLIGHT)|| intendName.equalsIgnoreCase(Constants.CLOSECURTAINS)) {
			return Constants.minPercent;
		}
		return StringUtils.EMPTY;

	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getPersentage() {
		return persentage;
	}

	public void setPersentage(String persentage) {
		this.persentage = persentage;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getIntentName() {
		return intentName;
	}

	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}

	public String getDeviceFuturePosition() {
		return deviceFuturePosition;
	}

	public void setDeviceFuturePosition(String deviceFuturePosition) {
		this.deviceFuturePosition = deviceFuturePosition;
	}

	public String getDeviceCMD() {
		return deviceCMD;
	}

	public void setDeviceCMD(String deviceCMD) {
		this.deviceCMD = deviceCMD;
	}

}
