package com.init;

import com.bean.Device;
import com.datasource.DbPoolConnection;
import com.datasource.RedisDAO;
import com.utility.PropertiesUtil;
import com.utility.ToHexUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Servlet implementation class InitializeData
 */
public class ConstantsMethod {

	
	public static void addDefualRoom(String accessToken, String roomName) {

		Constants.defualtRooms.remove(accessToken);
		Constants.defualtRooms.put(accessToken, roomName);

		RedisDAO.deleteObject(Constants.dafualtRoomKey);
		RedisDAO.saveObject(Constants.dafualtRoomKey, Constants.defualtRooms);
	}

	@SuppressWarnings("unchecked")
	public static String getDefualRoom(String key) {

		if (Constants.defualtRooms.size() == 0) {
			Constants.defualtRooms = RedisDAO.getHashMap(Constants.dafualtRoomKey);
		}

		String defualRoom = Constants.defualtRooms.get(key);
		return defualRoom;
	}

	public static void updateDeviceLists(Set<Device> lists) {
		RedisDAO.deleteObject(Constants.deviceKey);
		RedisDAO.saveObject(Constants.deviceKey, lists);
	}
	
	
	public static String getProcessBarCmd(String persentage) {
		if (StringUtils.isBlank(persentage)) {
			return "0000";
		}
		return ToHexUtil.pad(ToHexUtil.toHex(Integer.parseInt(persentage)), 4, true);
	}
	
	public static synchronized  void reSetDevicedata(Device old,Device newData){
		Constants.deviceList.remove(old);
		Constants.deviceList.add(newData);
		updateDeviceLists(Constants.deviceList);
	}
	
	public static   void reSetDevicedata(Device old,String moveToProcessBar){
		
		Runnable runnable = () -> {
			Device newData = old;
			newData.setProgressBar(moveToProcessBar+"");
			ConstantsMethod.reSetDevicedata(old, newData);
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public static void initData() {
		// load the data from Properties file
		Properties config = PropertiesUtil.loadPropertyFile("config.properties");
		Constants.serverIpConnectSocket = (String) config.get("serverIpConnectSocket");
		Constants.socketPort = (String) config.get("socketPort");
		Constants.redisIpConnectSocket = (String) config.get("redisIpConnectSocket");
		Constants.redisPort = (String) config.get("redisPort");
		Constants.currentServerPath = (String) config.get("currentServerPath");
		Constants.helpContent =  (String) config.get("helpContent");
		
		Constants.clientId =  (String) config.get("clientId");
		Constants.clientSecret =  (String) config.get("clientSecret");
		
		// reload the data from redis
		Set<Device> tempdeviceList = RedisDAO.getHashSet(Constants.deviceKey);
		if (tempdeviceList != null)
			Constants.deviceList = tempdeviceList;

		@SuppressWarnings("unchecked")
		Map<String, String> tempdefualtRooms = RedisDAO.getHashMap(Constants.dafualtRoomKey);
		if (tempdefualtRooms != null)
			Constants.defualtRooms = tempdefualtRooms;

		DbPoolConnection.getInstance();
	}
	
	
	

}
