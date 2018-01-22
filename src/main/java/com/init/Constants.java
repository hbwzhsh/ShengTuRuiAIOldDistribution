package com.init;

import com.model.House;
import com.model.IntendParams;
import com.model.User;
import com.netty.NettySendService;
import com.netty.model.Device;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Servlet implementation class InitializeData
 */
public class Constants {

	// those keys and values for redis
	public static String deviceKey = "DEVICEKEY";
	public static String dafualtRoomKey = "DAFUALTROOMKEY";
	public static Map<String, String> defualtRooms = new HashMap<String, String>();
	public static Map<String, Object> logicDeviceList = new HashMap<>();
	public static Set<House> roomList = new HashSet<House>();

	// cmd Body
	public static final String TURNONLIGHT = "turnonlight";
	public static final String TURNOFFLIGHT = "turnofflight";
	public static final String CLOSECURTAINS = "closecurtains";
	public static final String OPENCURTAINS = "opencurtains";
	public static final String DIMLIGHT = "dimlight";
	public static final String OPENCURTAINALITTLE="opencurtainalittle";
	public static final String CLOSECURTAINALITTLE="closecurtainalittle";
	
	public static final String OPENTHECURTAINHALFWAY="openthecurtainhalfway";
	
	public static final int MOVECURTAINSPERCENTS = 20;
	

	// head body
	public final static String SETDEFAULTROOM = "setdefaultroom";
	public final static String REFRESHDATA = "refreshdata";
	public final static String GETDEFAULTROOM = "getdefaultroom";

	// head body
	public final static String HELPINTENT = "amazon.helpintent";
	public final static String STOPINTENT = "amazon.stopintent";
	public final static String CANCELINTENT = "amazon.cancelintent";

	public static final String openCmd = "1";
	public static final String closeCmd = "0";
	public static final String dimlightCmd = "2";
	public static final String percentcurtain = "3";

	public static final String commonDefualtRoom = "living room";
	public static final String wholeHouse = "house";

	// for redis and socket
	public static String serverIpConnectSocket = "115.28.190.86";
//	public static String serverIpConnectSocket = "localhost";
	public static int socketPort = 19000;

	public static String redisIpConnectSocket = StringUtils.EMPTY;
	public static String redisPort = StringUtils.EMPTY;
	public static String currentServerPath = StringUtils.EMPTY;
	public static String helpContent = StringUtils.EMPTY;

}
