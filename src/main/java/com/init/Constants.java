package com.init;

import com.bean.Device;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Servlet implementation class InitializeData
 */
public class Constants {

	// those keys and values for redis
	public static String deviceKey = "DEVICEKEY";
	public static String dafualtRoomKey = "DAFUALTROOMKEY";
	public static Set<Device> deviceList = new HashSet<Device>();
	public static Map<String, String> defualtRooms = new HashMap<String, String>();

	// cmd Body
	public static final String TURNONLIGHT = "turnonlight";
	public static final String TURNOFFLIGHT = "turnofflight";
	public static final String CLOSECURTAINS = "closecurtains";
	public static final String OPENCURTAINS = "opencurtains";
	public static final String DIMLIGHT = "dimlight";
	public static final String OPENCURTAINALITTLE="opencurtainalittle";
	public static final String CLOSECURTAINALITTLE="closecurtainalittle";
	
	public static final String OPENTHECURTAINHALFWAY="openthecurtainhalfway";
	
	public static final int MOVECURTAINSPERCENTS=20;
	

	// head body
	public final static String SETDEFAULTROOM = "setdefaultroom";
	public final static String REFRESHDATA = "refreshdata";
	public final static String GETDEFAULTROOM = "getdefaultroom";

	// head body
	public final static String HELPINTENT = "amazon.helpintent";
	public final static String STOPINTENT = "amazon.stopintent";
	public final static String CANCELINTENT = "amazon.cancelintent";

	public static final String openCmd = "00";
	public static final String closeCmd = "01";
	public static final String dimlightCmd = "02";
	public static final String percentcurtain = "03";

	public static final String commonDefualtRoom = "living room";
	public static final String wholeHouse = "house";

	// for redis and socket
	public static String serverIpConnectSocket = StringUtils.EMPTY;
	public static String socketPort = StringUtils.EMPTY;
	public static String redisIpConnectSocket = StringUtils.EMPTY;
	public static String redisPort = StringUtils.EMPTY;
	public static String currentServerPath = StringUtils.EMPTY;
	public static String helpContent = StringUtils.EMPTY;
	
	public static String clientId = StringUtils.EMPTY;
	public static String clientSecret = StringUtils.EMPTY;

}
