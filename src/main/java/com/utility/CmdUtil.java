package com.utility;


/**
 * 
 * @author weiTaZhuang
 * @date 2016年6月7日 下午7:10:21
 * @Description 命令Util
 */

public class CmdUtil {
	public static final String TITLE = "7676";
	public static final String END = "0D0A";
	
	
	/** 手机系统*/
	public static final String System = "01"; //Android
	
	/** 发现主机协议号*/
	public static final String FindComputerProtocol = "01";
	/** 发现主机响应*/
	public static final String FindComputerAckProtocol = "10";
	
	
	
	/** 查找指定主机*/
	public static final String FindOneComputerAckProtocol = "02";
	
	
	/** 连接主机协议号*/
	public static final String ConnectComputerProtocol = "04";
	/** 连接主机相应成功*/
	public static final String ConnectComputerOk = "01";//400101
	/** 连接主机相应失败*/
	public static final String ConnectComputerNo = "00";//400100
	
	
	/** 添加主机协议号*/
	public static final String addComputerProtocol = "03";
	/** add主机相应成功*/
	public static final String addComputerOk = "01";//300101
	
	/** 未约定的协议号*/
	public static final String retainProtocol = "FF";
	
	public static final String USERID = "01";
	public static final String SUB_USERID = "02";
	
	/** 获取物理设备*/
	public static final String GET_DEVICE = "GET\\DEVICE\\";
	public static final String GET_DEVICE_ACK = "GET\\DEVICE\\ACK:";
	public static final String GET_DEVICE_FINISHED = "GET\\DEVICE\\FINISHED:";
	
	/** 获取逻辑设备*/
	public static final String GET_ENDPOINT = "GET\\ENDPOINT\\";
	public static final String GET_ENDPOINT_ACK = "GET\\ENDPOINT\\ACK:";
	public static final String GET_ENDPOINT_FINISHED = "GET\\ENDPOINT\\FINISHED:";
	
	/** 修改逻辑设备名称*/
	public static final String SET_ENDPOINT_NAME = "SET\\ENDPOINT\\NAME:";
	public static final String SET_ENDPOINT_NAME_ACK = "SET\\ENDPOINT\\NAME\\ACK:";
	
	/** 修改物理设备名称*/
	public static final String SET_DEVICE_NAME = "SET\\DEVICE\\NAME:";
	public static final String SET_DEVICE_NAME_ACK = "SET\\DEVICE\\NAME\\ACK:";
	 

	/** 获取情景列表*/
	public static final String GET_SCENE = "GET\\SCENE";
	public static final String GET_SCENE_ACK = "GET\\SCENE\\ACK:";
	public static final String GET_SCENE_FINISHED = "GET\\SCENE\\FINISHED:";
 

	/** 执行情景*/
	public static final String EXEC_SCENE = "SCENE\\EXEC:";
	public static final String ASCENE = "ASCENE:";
	public static final String EXEC_SCENE_DETAIL = "ASCENE\\DETAIL:"; 
	public static final String EXEC_SCENE_DETAIL_FINISHED = "ASCENE\\DETAIL\\FINISHED:"; 
	
	/** 删除情景*/
	public static final String DELTTE_SCENE = "DEL\\SCENE:";
	public static final String DELTTE_SCENE_ACK = "DEL\\SCENE\\ACK:"; 
	
	/** 添加情景*/
	public static final String ADD_SCENE = "ADD\\SCENE:";
	public static final String ADD_SCENE_ACK = "ADD\\SCENE\\ACK:"; 
	
	/** 修改情景*/
	public static final String CHG_SCENE = "CHG\\SCENE:";
	public static final String CHG_SCENE_ACK = "CHG\\SCENE\\ACK:"; 
 
	
	/** 发送命令*/
	public static final String CMD = "CMD:";
	public static final String ACMD = "ACMD:";//应答
	
	/** 设备在线*/
	public static final String DEV_ONLINE = "ONLINE:"; 
	/** 设备状态*/
	public static final String DEV_DAT = "DAT:"; 
	/** 传感器历史状态*/
	public static final String DEV_HSENSOR = "HSENSOR:"; 
	
	
	
	/** 添加设备应答*/
	public static final String DEVACK = "DEV-ACK:"; 
	/** 删除设备应答*/
	public static final String DEV_REMOVE_ACK = "DEV-REMOVE-ACK:"; 
	 
	
	/** 获取简易事件*/
	public static final String GET_EVT_SIMPLE = "GET\\EVT\\SIMPLE"; 
	/** 获取简易事件应答*/
	public static final String GET_EVT_SIMPLE_ACK = "GET\\EVT\\SIMPLE\\ACK:"; 
	/** 获取简易事件应答结束*/
	public static final String GET_EVT_SIMPLE_ACK_FINISHED = "GET\\EVT\\SIMPLE\\FINISHED:"; 
	
	/** 获取事件应答*/
	public static final String GET_EVT_ACK = "GET\\EVT"; 
	/** 获取事件应答结束*/
	public static final String GET_EVT_ACK_FINISHED = "GET\\EVT\\FINISHED:"; 
	
	/** 获取设备事件应答*/
	public static final String GET_EVT_ACK_DEVICE = "GET\\EVT\\ACK\\DEVICE:"; 
	/** 获取定时事件应答*/
	public static final String GET_EVT_ACK_TIMER = "GET\\EVT\\ACK\\TIMER:"; 
	/** 获取逻辑事件应答*/
	public static final String GET_EVT_ACK_LOGIC = "GET\\EVT\\ACK\\LOGIC:"; 
	/** 获取定时事件应答*/
	public static final String GET_EVT_ACK_TIMEOUT = "GET\\EVT\\ACK\\TIMEOUT:"; 
	/** 获取用户事件应答*/
	public static final String GET_EVT_ACK_USER = "GET\\EVT\\ACK\\USER:"; 
	
	/** 添加事件应答*/
	public static final String EVT_ACK = "EVT\\ACK:"; 
	
	/** 添加删除事件应答*/
	public static final String ADD_EVENT_SIMPLE_ACK = "ADD\\EVT\\SIMPLE\\ACK:"; 
	
	/** 删除事件应答*/
	public static final String DEV_EVENT_ACK = "EVT\\REMOVE\\ACK:"; 
	/** 删除简易事件应答*/
	public static final String DEV_EVENT_SIMPLE_ACK = "DEL\\EVT\\SIMPLE\\ACK:"; 
	
	/** 事件通知更新*/
	public static final String EVT_STATUS_UPDATE = "EVT\\STATUS\\UPDATE:"; 
	/** 数据备份回应*/
	public static final String DATA_UPLOAD = "MEM\\READ\\ACK:"; 
	/** 数据下载回应*/
//	public static final String DATA_DOWNLOAD = "MEM\\ERASE\\ACK:"; 
	public static final String DATA_DOWNLOAD = "MEM\\WRITE\\FINISHED\\ACK:"; 
	
	/** 删除消息与相关联动回应*/
	public static final String DEL_MSG_ACK = "DEL\\MSG\\ACK:"; 
	
	/** 新消息通知，服务器发送的*/
	public static final String SEND_MSG_ACK = "SEND\\MSG\\ACK:success"; 
	
	
	
	/** 获取联动应答*/
	public static final String GET_LNK_ACK = "GET\\LNK"; 
	/** 获取联动应答结束*/
	public static final String GET_LNK_ACK_FINISHED = "GET\\LNK\\FINISHED:"; 
	/** 获取DEV>DEV应答*/
	public static final String GET_LNK_ACK_DEV_DEV = "GET\\LNK\\ACK\\DEV>DEV:"; 
	/** 获取CMD>CMD应答*/
	public static final String GET_LNK_ACK_CMD_CMD = "GET\\LNK\\ACK\\CMD>CMD:"; 
	/** 获取EVT>CMDE应答*/
	public static final String GET_LNK_ACK_EVT_CMD = "GET\\LNK\\ACK\\EVT>CMD:"; 
	/** 获取EVT>SCENE应答*/
	public static final String GET_LNK_ACK_EVT_SCENE = "GET\\LNK\\ACK\\EVT>SCENE:"; 
	/** 获取EVT>MSG应答*/
	public static final String GET_LNK_ACK_EVT_MSG = "GET\\LNK\\ACK\\EVT>MSG:"; 
	/** 获取CMD>SCENE应答*/
	public static final String GET_LNK_ACK_CMD_SCENE = "GET\\LNK\\ACK\\CMD>SCENE:"; 
	
	
	/** 添加联动应答*/
	public static final String ADD_LNK_ACK = "ADD\\LNK\\ACK:"; 
	public static final String DEL_LNK_ACK = "DEL\\LNK\\ACK:"; 
	
	
	
	
	
	
	
	
	/**
	 * 发现主机
	 */
	public static String findComputerMsg(String email) {
		String protocolNum = FindComputerProtocol;
		
		String Email = null;
		try {
			byte[] data = email.getBytes("UTF-8");
			Email = ToHexUtil.byte2HexStr(data, data.length);
//			MLog.e("Email="+Email);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String EmailLen = get1Len(Email);
//		MLog.e("EmailLen="+EmailLen);
		String msgContent = EmailLen+Email+System;
//		MLog.e("msgContent="+msgContent);
		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + msgContent));
		
//		MLog.e("crc8="+crc8);
		String baoLen = get2Len(protocolNum + msgContent + crc8);
//		MLog.e("baoLen="+baoLen);
//      "App登陆邮箱长度	App登陆邮箱	App手机系统"
//		"格式	起始位	包长度	协议号	信息内容	错误校验	停止位"
//		"	     2	      2	     1	     N	      1	      2 "
		String bao = TITLE + baoLen +protocolNum+ msgContent+ crc8 + END;
		
		return bao;
	}
	
	
	/**
	 * 查找指定主机
	 * @param mac 
	 */
	public static String findOneComputerMsg(String email, String mac) {
		String protocolNum = FindOneComputerAckProtocol;
		
		String Email = null;
		String Mac = null;
		try {
			byte[] data = email.getBytes("UTF-8");
			Email = ToHexUtil.byte2HexStr(data, data.length);
			byte[] macdata = mac.getBytes("UTF-8");
			Mac = ToHexUtil.byte2HexStr(macdata, macdata.length);
//			MLog.e("Mac="+Mac);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String EmailLen = get1Len(Email);
//		MLog.e("EmailLen="+EmailLen);
		String msgContent = EmailLen+Email+System+Mac;
//		MLog.e("msgContent="+msgContent);
		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + msgContent));
//		MLog.e("crc8="+crc8);
		
		String baoLen = get2Len(protocolNum + msgContent + crc8);
		String bao = TITLE + baoLen +protocolNum+ msgContent+ crc8 + END;
//		MLog.e("email：" + email);
		
		return bao;
	}
	
	/**
	 * 添加主机
	 */
	public static String addComputerMsg(String email,String urll) {
		
		String protocolNum = addComputerProtocol;
		String userid = USERID;
		
		String Email = null;
		
		String URLL=null;
		
		try {
			byte[] data = email.getBytes("UTF-8");
			Email = ToHexUtil.byte2HexStr(data, data.length);//邮箱
			
		    byte[] urlldata=urll.getBytes("UTF-8");
		    
		   URLL=  ToHexUtil.byte2HexStr(urlldata, urlldata.length);
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String EmailLen = get1Len(Email);
		String  URLLLen=get1Len(URLL);
		String msgContent = EmailLen+Email+URLLLen+URLL;
		
//		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + userid + msgContent));
//		String cip = protocolNum + userid + msgContent + crc8;
//		MLog.e("加密前：" + cip);
//		String mi = encrypt(cip,mAesUtil);
//		MLog.e("密文：" + mi);
//		String miLen = get2Len(mi);
//
//		String bao = TITLE + miLen + mi + END;
		
		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + msgContent));
		String baoLen = get2Len(protocolNum + msgContent + crc8);
		String bao = TITLE + baoLen +protocolNum+ msgContent+ crc8 + END;
		
		
		return bao;
	}
	
	


	
	
	
	/**
	 * 获取情景列表
	 */
	public static String getScene() {
		return "GET\\SCENE:";
	}
	
	/**
	 * 向服务器发送 数据备份的命令
	 */
	public static String getUpload() {
		return "MEM\\READ:";
	}
	
	
	
	/**
	 * 修改情景
	 */
	public static String modificationScene(String old_name, String old_ico,String new_name, String new_ico) {
		return CHG_SCENE+old_name + "\\" + old_ico + "\\" + new_name + "\\"+ new_ico;
	}
	
	/**
	 * 删除情景
	 */
	public static String deleteScene(String name) {
		return DELTTE_SCENE+ name;
	}
	
	/**
	 * 执行情景
	 */
	public static String execScene(String name) {
		return EXEC_SCENE+ name;
	}
	
	
	/**
	 * 发送命令
	 */
	public static String execCmd(String name) {
		return CMD+ name;
	}
	 
	 
	/**
	 * 获取获取逻辑设备列表
	 */
	public static String getEndPoint() {
		return "GET\\ENDPOINT:";
	}
	
	 
	/**
	 * 获取逻辑设备描述
	 */
	public static String getDeviceDesc() {
		return "GET\\DEVID\\DESC:";
	}
	/**
	 * 获取最新状态
	 */
	public static String getStatus() {
		return "GET\\STATUS:";
	}
	/**
	 * 获取获取物理设备列表
	 */
	public static String getDifferenceTime(String time) {
		return "SET\\TIME\\OFFSET:"+time;
	}
	/**
	 * 获取获取物理设备列表
	 */
	public static String getDevice() {
		return "GET\\DEVICE:";
	}
	
	/**
	 * 添加物理设备 
	 * @param newName 
	 * @param newMac 
	 */
	public static String addDevice(String newMac, String newName) {
		return "DEV-INSERT:"+newName+"\\"+newMac;
	}
	
	/**
	 * 删除物理设备 
	 * @param mac 
	 */
	public static String delDevice(String mac) {
		return "DEV-REMOVE:"+mac;
	}
	
	
	/**
	 * 删除事件 
	 * @param name 
	 */
	public static String delEvent(String name) {
		return "EVT\\REMOVE:"+name;
	}
	
	/** 获取事件列表 */
	public static String getEvent() {
		return "GET\\EVT:";
	}
	
	/** 获取简易事件列表 */
	public static String getEasyEvent() {
		return "GET\\EVT\\SIMPLE:";
	}
	
	/** 删除简易事件 */
	public static String delEasyEvent(String name) {
		return "DEL\\EVT\\SIMPLE:"+name;
	}
	
	/** 执行用户事件 */
	public static String execUserEvent(String name,String status) {
		return "EVT\\USER\\EXEC:"+name+"\\"+status;
	}
	
	/** 添加用户事件 */
	public static String addUserEvent(String name) {
		return "EVT\\USER\\INSERT:"+name;
	}
	
	/** 添加简易事件 */
	public static String addEasyEvent(String cmd) {
		return "ADD\\EVT\\SIMPLE:"+cmd;
	}
	
	/** 添加设备事件 */
	public static String addDeviceEvent(String name,String model,String path,String desc, String mac,String ep,String attrid,String value0,String value1) {
		return "EVT\\DEVICE\\INSERT:"+name+"\\"+model+"\\"+path+"\\"+desc+"\\"+mac+"\\"+ep+"\\"+attrid+"\\"+value0+value1;
	}
	
	/** 添加超时事件 */
	public static String addTimeOutEvent(String name,String name_a,String value) {
		return "EVT\\TIMEOUT\\INSERT:"+name+"\\"+name_a+"\\"+value;
	}
	
	/** 添加逻辑事件 */
	public static String addLogicEvent(String name,String model,String name_a,String name_b) {
		return "EVT\\LOGIC\\INSERT:"+name+"\\"+model+"\\"+name_a+"\\"+name_b;
	}
	
	/** 添加逻辑事件 */
	public static String delNews(String name) {
		return "DEL\\MSG:"+name;
	}
	
	/** 添加定时事件 */
	public static String addTimeEvent(String name,String weeks,String mode,String time0,String time1) {
		return "EVT\\TIMER\\INSERT:"+name+"\\"+weeks+"\\"+mode+"\\"+time0+"\\"+time1;
	}
	
	/** 获取联动列表 */
	public static String getLink() {
		return "GET\\LNK:";
	}
	
	/** 添加LinkDEV_DEV */
	public static String addLinkDEV_DEV(String in_path, String in_mac, String in_ep, String out_path, String out_mac, String out_ep) {
		return "ADD\\LNK\\DEV>DEV:"+in_path+"\\"+in_mac+"\\"+in_ep+"\\"+out_path+"\\"+out_mac+"\\"+out_ep;
	}
	
	/** 添加LinkCMD>CMD */
	public static String addLinkCMD_CMD(String in_path, String in_desc, String in_mac, String in_ep, String in_cmdid, 
			String in_op, String out_path, String out_desc, String out_mac, String out_ep, String out_cmdid, 
			String out_op) {
		return "ADD\\LNK\\CMD>CMD:"+in_path+"\\"+in_desc+"\\"+in_mac+"\\"+in_ep+"\\"+in_cmdid+"\\"+in_op+"\\"+
							       out_path+"\\"+out_desc+"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}
	
	/** 添加LinkEVT>CMD */
	public static String addLinkEVT_CMD(String in_name, String out_path, String out_desc, String out_mac,
			String out_ep, String out_cmdid, String out_op) {
		return "ADD\\LNK\\EVT>CMD:"+in_name+"\\"+out_path+"\\"+out_desc+"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}
	
	
	/** 添加LinkEVT>SCENE */
	public static String addLinkEVT_SCENE(String in_name, String out_name) {
		return "ADD\\LNK\\EVT>SCENE:"+in_name+"\\"+out_name;
	}
	
	/** 添加LinkEVT>MSG */
	public static String addLinkEVT_MSG(String in_name, String out_name) {
		return "ADD\\LNK\\EVT>MSG:"+in_name+"\\"+out_name;
	}
	
	/** 添加LinkCMD>SCENE */
	public static String addLinkCMD_SCENE(String in_path, String in_desc, String in_mac, String in_ep, String in_cmdid, 
			String in_op, String out_name) {
		return "ADD\\LNK\\CMD>SCENE:"+in_path+"\\"+in_desc+"\\"+in_mac+"\\"+in_ep+"\\"+in_cmdid+"\\"+in_op+"\\"+out_name;
	}
	
	public static String delLinkCMD_SCENE(String in_mac, String in_ep,  String in_cmdid, String in_op, String out_name) {
		return "DEL\\LNK\\CMD>SCENE:"+in_mac+"\\"+in_ep+"\\"+in_cmdid+"\\"+in_op+"\\"+out_name;
	}


	public static String delLinkEVT_MSG(String in_name, String out_name) {
		return "DEL\\LNK\\EVT>MSG:"+in_name+"\\"+out_name;
	}


	public static String delLinkEVT_SCENE(String in_name, String out_name) {
		return "DEL\\LNK\\EVT>SCENE:"+in_name+"\\"+out_name;
	}


	public static String delLinkEVT_CMD(String in_name, String out_mac, String out_ep, String out_cmdid,
			String out_op) {
		return "DEL\\LNK\\EVT>CMD:"+in_name+"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}


	public static String delLinkCMD_CMD(String in_mac, String in_ep, String in_cmdid, String in_op, String out_mac,
			String out_ep, String out_cmdid, String out_op) {
		return "DEL\\LNK\\CMD>CMD:"+in_mac+"\\"+in_ep+"\\"+in_cmdid+ "\\"+in_op +"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}


	public static String delLinkDEV_DEV(String in_mac, String in_ep, String out_mac, String out_ep) {
		return "DEL\\LNK\\DEV>DEV:"+in_mac+"\\"+in_ep+"\\"+out_mac+"\\"+out_ep;
	}

	
	
	
	/**
	 * 连接服务器
	 */
	public static String connectService(AesUtil mAesUtil ,String mac) {
		String userid = USERID;
		/*if (application.isChildren()) {
			int children = application.getChildrenId();
			if (children<255) {
				userid = ToHexUtil.pad(Integer.toHexString(children), 2, true);
			}
//			MLog.e("连接服务器 userid：" + userid);
		}*/
		
		
		String msgContent = "";
		
		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte("FE" + userid));
		String cip = "FE" + userid + msgContent + crc8;
//		MLog.e("加密前：" + cip);
		
		String mi = encrypt(cip,mAesUtil);
		String miLen = get2Len(mi);
		String msg = mac + TITLE + miLen + mi + END;
//		MLog.e("mi="+mi);
//		MLog.e("连接服务器 msg "+mac);
		return msg;
	}
	
	/**
	 * 命令转发服务器
	 */
	public  static String getToService(AesUtil mAesUtil ,String cmd, String mac) {
		
		String protocolNum = retainProtocol;
		String userid = USERID;
			/*int children = 21; 
			if (children<255) {
				userid = ToHexUtil.pad(Integer.toHexString(children), 2, true);
			}*/
//			MLog.e("命令转发服务器 userid：" + userid);   
		String msgContent=""/* = ToHexUtil.string2ASCII(cmd)*/;
		
		try {
			byte[] msgdata = cmd.getBytes("UTF-8");
			msgContent = ToHexUtil.byte2HexStr(msgdata, msgdata.length);
			
//			byte[] macData = mac.getBytes("UTF-8");
//			Mac = ToHexUtil.byte2HexStr(macData, macData.length);
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + userid + msgContent));
		String cip = protocolNum + userid + msgContent + crc8;
//		MLog.S("转发 加密前：" + cip);
		String mi = encrypt(cip,mAesUtil);
//		MLog.e("密文：" + mi);
		String miLen = get2Len(mi);

		String msg =mac+ TITLE + miLen + mi + END;
		
		return msg;
	}
	
	/**
	 * 添加主机
	 */
//	public static String getAddComputerMsg(String email,AesUtil mAesUtil) {
//		String protocolNum = "03";
//		String userid = USERID;
////		email = "123456789abc@qq.com";
//
//		String emailLen = getLen(email.length());
////		MLog.e("emailLen=" + emailLen);
//
//		email = ToHexUtil.str2HexStr(email);//字符串转换成十六进制字符串
////		MLog.e("email=" + email);
//
//		String msgContent = emailLen + email;
////		MLog.e("msgContent=" + msgContent);
//
//		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + userid + msgContent));
////		MLog.e("crc8=" + crc8);
//		
//		String cip = protocolNum + userid + msgContent + crc8;
////		MLog.e("加密前：" + cip);
//
//		String mi = encrypt(cip,mAesUtil);
////		MLog.e("密文=" + mi);
//
//		String miLen = get2Len(mi);
////		MLog.e("miLen=" + miLen);
//
//		String msg = TITLE + miLen + mi + END;
//		MLog.e("添加主机：" + msg);
//		return msg;
//	}
	
	/** 10进制转16进制,return 1字节*/
	public static String getLen(int len) {
		if (len<16) {
			return "0"+Integer.toHexString(len).toUpperCase();
		}
		return Integer.toHexString(len).toUpperCase();
	}

	/** 获取16进制字符串的长度,return 1字节*/
	public static String get1Len(String string) {
		
		int milen = ToHexUtil.hexStringToByte(string).length;
		String miLen = "";
		if (milen <= 15) {
			miLen = "0" + Integer.toHexString(milen).toUpperCase();
		}else{
			miLen =Integer.toHexString(milen).toUpperCase();
		}
		return miLen;
	}
	
	/** 获取16进制字符串的长度,return 2字节*/
	public static String get2Len(String string) {
		
		int milen = ToHexUtil.hexStringToByte(string).length;
		String miLen = "";
		if (milen <= 15) {
			miLen = "000" + Integer.toHexString(milen).toUpperCase();
		} else if (milen > 15 && milen <= 255) {
			miLen = "00" + Integer.toHexString(milen).toUpperCase();
		} else if (milen > 255 && milen <= 4095) {
			miLen = "0" + Integer.toHexString(milen).toUpperCase();
		}else{
			miLen =Integer.toHexString(milen).toUpperCase();
		}
		return miLen;
	}
	
	/**
	 * 加密
	 * @param mAesUtil 
	 * @string 16进制字符串
	 */
	private synchronized static String encrypt(String string, AesUtil mAesUtil) {
		byte [] content = ToHexUtil.hexStringToByte(string);
		byte [] decode = mAesUtil.encrypt(content);
		String c =ToHexUtil.byte2HexStr(decode, decode.length);
		return c;
	}

	/**
	 * 加密测试
	 */
	public static void jiami() {
		AesUtil mAesUtil = new AesUtil();
		
		String t1 = "040100";//连接圣图瑞主机请求
		String t1_ = "40010107";//智能主机响应
		
		String t = t1;
		 
		byte[] a = ToHexUtil.hexStringToByte(t);
		
		byte[] b = mAesUtil.encrypt(a, AesUtil.Key, AesUtil.IV);
		String c= ToHexUtil.byte2HexStr(b,b.length);
		
		byte[]  de  =mAesUtil.decrypt(b,  AesUtil.Key, AesUtil.IV);
		String dec= ToHexUtil.byte2HexStr(de,de.length);

		
		//---------------中文-----------------
		String c11= mAesUtil.encrypt("40010107", AesUtil.Key, AesUtil.IV);
		String  de11  =mAesUtil.decrypt(c11,  AesUtil.Key, AesUtil.IV);
	}
	
}
