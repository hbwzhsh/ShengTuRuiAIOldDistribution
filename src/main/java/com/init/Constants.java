package com.init;

import com.bean.Device;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Servlet implementation class InitializeData
 */
@Component
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

	@Value("${serverIpConnectSocket}")
	public static String serverIpConnectSocket ="115.28.190.86" ;
	@Value("${socketPort}")
	public static String socketPort ="8888";
	@Value("${redisIpConnectSocket}")
	public static String redisIpConnectSocket ="47.91.76.217" ;
	@Value("${redisPort}")
	public static String redisPort ="6380";
	@Value("${currentServerPath}")
	public static String currentServerPath ="https://deu-service.ctrcn.com/SmartHomeTwo12/";
	@Value("${helpContent}")
	public static String helpContent ="to control the your smart device just say open the house curtains, or open the living room curtain to 40%,or  turn on the living room lights,or dim the living room light to 40%";
	@Value("${clientId}")
	public static String clientId ="amzn1.application-oa2-client.abe56669bd8f4a918868fa009dd7b534";
	@Value("${clientSecret}")
	public static String clientSecret="392196037b74849995e0bacd41c7f9625f1da21364038ce1d7b9974769384094" ;

}
