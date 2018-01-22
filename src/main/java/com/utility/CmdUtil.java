package com.utility;


/**
 * 
 * @author weiTaZhuang
 * @date 2016��6��7�� ����7:10:21
 * @Description ����Util
 */

public class CmdUtil {
	public static final String TITLE = "7676";
	public static final String END = "0D0A";
	
	
	/** �ֻ�ϵͳ*/
	public static final String System = "01"; //Android
	
	/** ��������Э���*/
	public static final String FindComputerProtocol = "01";
	/** ����������Ӧ*/
	public static final String FindComputerAckProtocol = "10";
	
	
	
	/** ����ָ������*/
	public static final String FindOneComputerAckProtocol = "02";
	
	
	/** ��������Э���*/
	public static final String ConnectComputerProtocol = "04";
	/** ����������Ӧ�ɹ�*/
	public static final String ConnectComputerOk = "01";//400101
	/** ����������Ӧʧ��*/
	public static final String ConnectComputerNo = "00";//400100
	
	
	/** �������Э���*/
	public static final String addComputerProtocol = "03";
	/** add������Ӧ�ɹ�*/
	public static final String addComputerOk = "01";//300101
	
	/** δԼ����Э���*/
	public static final String retainProtocol = "FF";
	
	public static final String USERID = "01";
	public static final String SUB_USERID = "02";
	
	/** ��ȡ�����豸*/
	public static final String GET_DEVICE = "GET\\DEVICE\\";
	public static final String GET_DEVICE_ACK = "GET\\DEVICE\\ACK:";
	public static final String GET_DEVICE_FINISHED = "GET\\DEVICE\\FINISHED:";
	
	/** ��ȡ�߼��豸*/
	public static final String GET_ENDPOINT = "GET\\ENDPOINT\\";
	public static final String GET_ENDPOINT_ACK = "GET\\ENDPOINT\\ACK:";
	public static final String GET_ENDPOINT_FINISHED = "GET\\ENDPOINT\\FINISHED:";
	
	/** �޸��߼��豸����*/
	public static final String SET_ENDPOINT_NAME = "SET\\ENDPOINT\\NAME:";
	public static final String SET_ENDPOINT_NAME_ACK = "SET\\ENDPOINT\\NAME\\ACK:";
	
	/** �޸������豸����*/
	public static final String SET_DEVICE_NAME = "SET\\DEVICE\\NAME:";
	public static final String SET_DEVICE_NAME_ACK = "SET\\DEVICE\\NAME\\ACK:";
	 

	/** ��ȡ�龰�б�*/
	public static final String GET_SCENE = "GET\\SCENE";
	public static final String GET_SCENE_ACK = "GET\\SCENE\\ACK:";
	public static final String GET_SCENE_FINISHED = "GET\\SCENE\\FINISHED:";
 

	/** ִ���龰*/
	public static final String EXEC_SCENE = "SCENE\\EXEC:";
	public static final String ASCENE = "ASCENE:";
	public static final String EXEC_SCENE_DETAIL = "ASCENE\\DETAIL:"; 
	public static final String EXEC_SCENE_DETAIL_FINISHED = "ASCENE\\DETAIL\\FINISHED:"; 
	
	/** ɾ���龰*/
	public static final String DELTTE_SCENE = "DEL\\SCENE:";
	public static final String DELTTE_SCENE_ACK = "DEL\\SCENE\\ACK:"; 
	
	/** ����龰*/
	public static final String ADD_SCENE = "ADD\\SCENE:";
	public static final String ADD_SCENE_ACK = "ADD\\SCENE\\ACK:"; 
	
	/** �޸��龰*/
	public static final String CHG_SCENE = "CHG\\SCENE:";
	public static final String CHG_SCENE_ACK = "CHG\\SCENE\\ACK:"; 
 
	
	/** ��������*/
	public static final String CMD = "CMD:";
	public static final String ACMD = "ACMD:";//Ӧ��
	
	/** �豸����*/
	public static final String DEV_ONLINE = "ONLINE:"; 
	/** �豸״̬*/
	public static final String DEV_DAT = "DAT:"; 
	/** ��������ʷ״̬*/
	public static final String DEV_HSENSOR = "HSENSOR:"; 
	
	
	
	/** ����豸Ӧ��*/
	public static final String DEVACK = "DEV-ACK:"; 
	/** ɾ���豸Ӧ��*/
	public static final String DEV_REMOVE_ACK = "DEV-REMOVE-ACK:"; 
	 
	
	/** ��ȡ�����¼�*/
	public static final String GET_EVT_SIMPLE = "GET\\EVT\\SIMPLE"; 
	/** ��ȡ�����¼�Ӧ��*/
	public static final String GET_EVT_SIMPLE_ACK = "GET\\EVT\\SIMPLE\\ACK:"; 
	/** ��ȡ�����¼�Ӧ�����*/
	public static final String GET_EVT_SIMPLE_ACK_FINISHED = "GET\\EVT\\SIMPLE\\FINISHED:"; 
	
	/** ��ȡ�¼�Ӧ��*/
	public static final String GET_EVT_ACK = "GET\\EVT"; 
	/** ��ȡ�¼�Ӧ�����*/
	public static final String GET_EVT_ACK_FINISHED = "GET\\EVT\\FINISHED:"; 
	
	/** ��ȡ�豸�¼�Ӧ��*/
	public static final String GET_EVT_ACK_DEVICE = "GET\\EVT\\ACK\\DEVICE:"; 
	/** ��ȡ��ʱ�¼�Ӧ��*/
	public static final String GET_EVT_ACK_TIMER = "GET\\EVT\\ACK\\TIMER:"; 
	/** ��ȡ�߼��¼�Ӧ��*/
	public static final String GET_EVT_ACK_LOGIC = "GET\\EVT\\ACK\\LOGIC:"; 
	/** ��ȡ��ʱ�¼�Ӧ��*/
	public static final String GET_EVT_ACK_TIMEOUT = "GET\\EVT\\ACK\\TIMEOUT:"; 
	/** ��ȡ�û��¼�Ӧ��*/
	public static final String GET_EVT_ACK_USER = "GET\\EVT\\ACK\\USER:"; 
	
	/** ����¼�Ӧ��*/
	public static final String EVT_ACK = "EVT\\ACK:"; 
	
	/** ���ɾ���¼�Ӧ��*/
	public static final String ADD_EVENT_SIMPLE_ACK = "ADD\\EVT\\SIMPLE\\ACK:"; 
	
	/** ɾ���¼�Ӧ��*/
	public static final String DEV_EVENT_ACK = "EVT\\REMOVE\\ACK:"; 
	/** ɾ�������¼�Ӧ��*/
	public static final String DEV_EVENT_SIMPLE_ACK = "DEL\\EVT\\SIMPLE\\ACK:"; 
	
	/** �¼�֪ͨ����*/
	public static final String EVT_STATUS_UPDATE = "EVT\\STATUS\\UPDATE:"; 
	/** ���ݱ��ݻ�Ӧ*/
	public static final String DATA_UPLOAD = "MEM\\READ\\ACK:"; 
	/** �������ػ�Ӧ*/
//	public static final String DATA_DOWNLOAD = "MEM\\ERASE\\ACK:"; 
	public static final String DATA_DOWNLOAD = "MEM\\WRITE\\FINISHED\\ACK:"; 
	
	/** ɾ����Ϣ�����������Ӧ*/
	public static final String DEL_MSG_ACK = "DEL\\MSG\\ACK:"; 
	
	/** ����Ϣ֪ͨ�����������͵�*/
	public static final String SEND_MSG_ACK = "SEND\\MSG\\ACK:success"; 
	
	
	
	/** ��ȡ����Ӧ��*/
	public static final String GET_LNK_ACK = "GET\\LNK"; 
	/** ��ȡ����Ӧ�����*/
	public static final String GET_LNK_ACK_FINISHED = "GET\\LNK\\FINISHED:"; 
	/** ��ȡDEV>DEVӦ��*/
	public static final String GET_LNK_ACK_DEV_DEV = "GET\\LNK\\ACK\\DEV>DEV:"; 
	/** ��ȡCMD>CMDӦ��*/
	public static final String GET_LNK_ACK_CMD_CMD = "GET\\LNK\\ACK\\CMD>CMD:"; 
	/** ��ȡEVT>CMDEӦ��*/
	public static final String GET_LNK_ACK_EVT_CMD = "GET\\LNK\\ACK\\EVT>CMD:"; 
	/** ��ȡEVT>SCENEӦ��*/
	public static final String GET_LNK_ACK_EVT_SCENE = "GET\\LNK\\ACK\\EVT>SCENE:"; 
	/** ��ȡEVT>MSGӦ��*/
	public static final String GET_LNK_ACK_EVT_MSG = "GET\\LNK\\ACK\\EVT>MSG:"; 
	/** ��ȡCMD>SCENEӦ��*/
	public static final String GET_LNK_ACK_CMD_SCENE = "GET\\LNK\\ACK\\CMD>SCENE:"; 
	
	
	/** �������Ӧ��*/
	public static final String ADD_LNK_ACK = "ADD\\LNK\\ACK:"; 
	public static final String DEL_LNK_ACK = "DEL\\LNK\\ACK:"; 
	
	
	
	
	
	
	
	
	/**
	 * ��������
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
//      "App��½���䳤��	App��½����	App�ֻ�ϵͳ"
//		"��ʽ	��ʼλ	������	Э���	��Ϣ����	����У��	ֹͣλ"
//		"	     2	      2	     1	     N	      1	      2 "
		String bao = TITLE + baoLen +protocolNum+ msgContent+ crc8 + END;

		return bao;
	}


	/**
	 * ����ָ������
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
//		MLog.e("email��" + email);

		return bao;
	}

	/**
	 * �������
	 */
	public static String addComputerMsg(String email,String urll) {

		String protocolNum = addComputerProtocol;
		String userid = USERID;

		String Email = null;

		String URLL=null;

		try {
			byte[] data = email.getBytes("UTF-8");
			Email = ToHexUtil.byte2HexStr(data, data.length);//����

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
//		MLog.e("����ǰ��" + cip);
//		String mi = encrypt(cip,mAesUtil);
//		MLog.e("���ģ�" + mi);
//		String miLen = get2Len(mi);
//
//		String bao = TITLE + miLen + mi + END;

		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + msgContent));
		String baoLen = get2Len(protocolNum + msgContent + crc8);
		String bao = TITLE + baoLen +protocolNum+ msgContent+ crc8 + END;


		return bao;
	}







	/**
	 * ��ȡ�龰�б�
	 */
	public static String getScene() {
		return "GET\\SCENE:";
	}

	/**
	 * ����������� ���ݱ��ݵ�����
	 */
	public static String getUpload() {
		return "MEM\\READ:";
	}



	/**
	 * �޸��龰
	 */
	public static String modificationScene(String old_name, String old_ico,String new_name, String new_ico) {
		return CHG_SCENE+old_name + "\\" + old_ico + "\\" + new_name + "\\"+ new_ico;
	}

	/**
	 * ɾ���龰
	 */
	public static String deleteScene(String name) {
		return DELTTE_SCENE+ name;
	}

	/**
	 * ִ���龰
	 */
	public static String execScene(String name) {
		return EXEC_SCENE+ name;
	}


	/**
	 * ��������
	 */
	public static String execCmd(String name) {
		return CMD+ name;
	}


	/**
	 * ��ȡ��ȡ�߼��豸�б�
	 */
	public static String getEndPoint() {
		return "GET\\ENDPOINT:";
	}


	/**
	 * ��ȡ�߼��豸����
	 */
	public static String getDeviceDesc() {
		return "GET\\DEVID\\DESC:";
	}
	/**
	 * ��ȡ����״̬
	 */
	public static String getStatus() {
		return "GET\\STATUS:";
	}
	/**
	 * ��ȡ��ȡ�����豸�б�
	 */
	public static String getDifferenceTime(String time) {
		return "SET\\TIME\\OFFSET:"+time;
	}
	/**
	 * ��ȡ��ȡ�����豸�б�
	 */
	public static String getDevice() {
		return "GET\\DEVICE:";
	}

	/**
	 * ��������豸
	 * @param newName
	 * @param newMac
	 */
	public static String addDevice(String newMac, String newName) {
		return "DEV-INSERT:"+newName+"\\"+newMac;
	}

	/**
	 * ɾ�������豸
	 * @param mac
	 */
	public static String delDevice(String mac) {
		return "DEV-REMOVE:"+mac;
	}


	/**
	 * ɾ���¼�
	 * @param name
	 */
	public static String delEvent(String name) {
		return "EVT\\REMOVE:"+name;
	}

	/** ��ȡ�¼��б� */
	public static String getEvent() {
		return "GET\\EVT:";
	}

	/** ��ȡ�����¼��б� */
	public static String getEasyEvent() {
		return "GET\\EVT\\SIMPLE:";
	}

	/** ɾ�������¼� */
	public static String delEasyEvent(String name) {
		return "DEL\\EVT\\SIMPLE:"+name;
	}

	/** ִ���û��¼� */
	public static String execUserEvent(String name,String status) {
		return "EVT\\USER\\EXEC:"+name+"\\"+status;
	}

	/** ����û��¼� */
	public static String addUserEvent(String name) {
		return "EVT\\USER\\INSERT:"+name;
	}

	/** ��Ӽ����¼� */
	public static String addEasyEvent(String cmd) {
		return "ADD\\EVT\\SIMPLE:"+cmd;
	}

	/** ����豸�¼� */
	public static String addDeviceEvent(String name,String model,String path,String desc, String mac,String ep,String attrid,String value0,String value1) {
		return "EVT\\DEVICE\\INSERT:"+name+"\\"+model+"\\"+path+"\\"+desc+"\\"+mac+"\\"+ep+"\\"+attrid+"\\"+value0+value1;
	}

	/** ��ӳ�ʱ�¼� */
	public static String addTimeOutEvent(String name,String name_a,String value) {
		return "EVT\\TIMEOUT\\INSERT:"+name+"\\"+name_a+"\\"+value;
	}

	/** ����߼��¼� */
	public static String addLogicEvent(String name,String model,String name_a,String name_b) {
		return "EVT\\LOGIC\\INSERT:"+name+"\\"+model+"\\"+name_a+"\\"+name_b;
	}

	/** ����߼��¼� */
	public static String delNews(String name) {
		return "DEL\\MSG:"+name;
	}

	/** ��Ӷ�ʱ�¼� */
	public static String addTimeEvent(String name,String weeks,String mode,String time0,String time1) {
		return "EVT\\TIMER\\INSERT:"+name+"\\"+weeks+"\\"+mode+"\\"+time0+"\\"+time1;
	}

	/** ��ȡ�����б� */
	public static String getLink() {
		return "GET\\LNK:";
	}

	/** ���LinkDEV_DEV */
	public static String addLinkDEV_DEV(String in_path, String in_mac, String in_ep, String out_path, String out_mac, String out_ep) {
		return "ADD\\LNK\\DEV>DEV:"+in_path+"\\"+in_mac+"\\"+in_ep+"\\"+out_path+"\\"+out_mac+"\\"+out_ep;
	}

	/** ���LinkCMD>CMD */
	public static String addLinkCMD_CMD(String in_path, String in_desc, String in_mac, String in_ep, String in_cmdid,
			String in_op, String out_path, String out_desc, String out_mac, String out_ep, String out_cmdid,
			String out_op) {
		return "ADD\\LNK\\CMD>CMD:"+in_path+"\\"+in_desc+"\\"+in_mac+"\\"+in_ep+"\\"+in_cmdid+"\\"+in_op+"\\"+
							       out_path+"\\"+out_desc+"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}

	/** ���LinkEVT>CMD */
	public static String addLinkEVT_CMD(String in_name, String out_path, String out_desc, String out_mac,
			String out_ep, String out_cmdid, String out_op) {
		return "ADD\\LNK\\EVT>CMD:"+in_name+"\\"+out_path+"\\"+out_desc+"\\"+out_mac+"\\"+out_ep+"\\"+out_cmdid+"\\"+out_op;
	}


	/** ���LinkEVT>SCENE */
	public static String addLinkEVT_SCENE(String in_name, String out_name) {
		return "ADD\\LNK\\EVT>SCENE:"+in_name+"\\"+out_name;
	}

	/** ���LinkEVT>MSG */
	public static String addLinkEVT_MSG(String in_name, String out_name) {
		return "ADD\\LNK\\EVT>MSG:"+in_name+"\\"+out_name;
	}

	/** ���LinkCMD>SCENE */
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
	 * ���ӷ�����
	 * @param application
	 */
	public static String connectService(AesUtil mAesUtil , String mac) {
		String userid = USERID;
		/*if (application.isChildren()) {
			int children = application.getChildrenId();
			if (children<255) {
				userid = ToHexUtil.pad(Integer.toHexString(children), 2, true);
			}
//			MLog.e("���ӷ����� userid��" + userid);
		}*/


		String msgContent = "";

		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte("FE" + userid));
		String cip = "FE" + userid + msgContent + crc8;
//		MLog.e("����ǰ��" + cip);

		String mi = encrypt(cip,mAesUtil);
		String miLen = get2Len(mi);
		String msg = mac + TITLE + miLen + mi + END;
//		MLog.e("mi="+mi);
//		MLog.e("���ӷ����� msg "+mac);
		return msg;
	}

	/**
	 * ����ת��������
	 * @param application
	 */
	public  static String getToService(AesUtil mAesUtil , String cmd, String mac) {

		String protocolNum = retainProtocol;
		String userid = USERID;
			/*int children = 21;
			if (children<255) {
				userid = ToHexUtil.pad(Integer.toHexString(children), 2, true);
			}*/
//			MLog.e("����ת�������� userid��" + userid);
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
//		MLog.S("ת�� ����ǰ��" + cip);
		String mi = encrypt(cip,mAesUtil);
//		MLog.e("���ģ�" + mi);
		String miLen = get2Len(mi);

		String msg =mac+ TITLE + miLen + mi + END;

		return msg;
	}

	/**
	 * �������
	 */
//	public static String getAddComputerMsg(String email,AesUtil mAesUtil) {
//		String protocolNum = "03";
//		String userid = USERID;
////		email = "123456789abc@qq.com";
//
//		String emailLen = getLen(email.length());
////		MLog.e("emailLen=" + emailLen);
//
//		email = ToHexUtil.str2HexStr(email);//�ַ���ת����ʮ�������ַ���
////		MLog.e("email=" + email);
//
//		String msgContent = emailLen + email;
////		MLog.e("msgContent=" + msgContent);
//
//		String crc8 = CRC8.calcCrc8(ToHexUtil.hexStringToByte(protocolNum + userid + msgContent));
////		MLog.e("crc8=" + crc8);
//
//		String cip = protocolNum + userid + msgContent + crc8;
////		MLog.e("����ǰ��" + cip);
//
//		String mi = encrypt(cip,mAesUtil);
////		MLog.e("����=" + mi);
//
//		String miLen = get2Len(mi);
////		MLog.e("miLen=" + miLen);
//
//		String msg = TITLE + miLen + mi + END;
//		MLog.e("���������" + msg);
//		return msg;
//	}

	/** 10����ת16����,return 1�ֽ�*/
	public static String getLen(int len) {
		if (len<16) {
			return "0"+Integer.toHexString(len).toUpperCase();
		}
		return Integer.toHexString(len).toUpperCase();
	}

	/** ��ȡ16�����ַ����ĳ���,return 1�ֽ�*/
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

	/** ��ȡ16�����ַ����ĳ���,return 2�ֽ�*/
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
	 * ����
	 * @param mAesUtil
	 * @string 16�����ַ���
	 */
	private synchronized static String encrypt(String string, AesUtil mAesUtil) {
		byte [] content = ToHexUtil.hexStringToByte(string);
		byte [] decode = mAesUtil.encrypt(content);
		String c = ToHexUtil.byte2HexStr(decode, decode.length);
		return c;
	}

	/**
	 * ���ܲ���
	 */
	public static void jiami() {
		AesUtil mAesUtil = new AesUtil();

		String t1 = "040100";//����ʥͼ����������
		String t1_ = "40010107";//����������Ӧ

		String t = t1;

		byte[] a = ToHexUtil.hexStringToByte(t);

		byte[] b = mAesUtil.encrypt(a, AesUtil.Key, AesUtil.IV);
		String c= ToHexUtil.byte2HexStr(b,b.length);
		
		byte[]  de  =mAesUtil.decrypt(b,  AesUtil.Key, AesUtil.IV);
		String dec= ToHexUtil.byte2HexStr(de,de.length);

		
		//---------------����-----------------
		String c11= mAesUtil.encrypt("40010107", AesUtil.Key, AesUtil.IV);
		String  de11  =mAesUtil.decrypt(c11,  AesUtil.Key, AesUtil.IV);
	}
	
}
