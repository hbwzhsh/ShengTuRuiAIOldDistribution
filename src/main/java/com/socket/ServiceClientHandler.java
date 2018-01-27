package com.socket;

import com.SpringUtil;
import com.bean.Device;
import com.utility.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServiceClientHandler extends IoHandlerAdapter {

	private String serviceEndRremain = "";
	private String hostEndRremain = "";

	private StringRedisTemplate stringRedisTemplate =(StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");
	private static final Logger logger = LogManager.getLogger(ServiceClientHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("-----------> session.getId(2222222222):"+session.getId());
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
		char[] mChars = "0123456789ABCDEF".toCharArray();
		byte[] bytes = (byte[]) message;
		StringBuilder cmdstr = new StringBuilder();
		for (int n = 0; n < bytes.length; n++) {
			cmdstr.append(mChars[(bytes[n] & 0xFF) >> 4]);
			cmdstr.append(mChars[bytes[n] & 0x0F]);// 0x0f����ʮ���Ƶ�15
		}
		cmdstr.toString().trim().toUpperCase(Locale.US);

		String cmdData = cmdstr.toString();
		if (!StringUtils.isEmpty(serviceEndRremain)) {
			cmdData = serviceEndRremain + cmdData;
			serviceEndRremain = "";
		}

		if (cmdData.indexOf("0D0A") < 0) {
			hostEndRremain = cmdData;
			return;
		}

		if (!cmdData.endsWith("0D0A")) {
			int endIndex = cmdData.lastIndexOf("0D0A");
			serviceEndRremain = cmdData.substring(endIndex + 4);
			cmdData = cmdData.substring(0, endIndex + 4);
		}

		AesUtil mAesUtil = SocketFactory.getCurAesInstance(session.getId()+"");
		List<String> cmdList = new ArrayList<String>();
		List<String> macList = new ArrayList<String>();

		segmentService(cmdData, cmdList, macList);

		for (int i = 0; i < cmdList.size(); i++) {
			String loopcmd = cmdList.get(i);
			String loopmac = macList.get(i);
			String singleItem = parse(loopcmd, mAesUtil, true);

			if (StringUtils.isNotBlank(singleItem) && singleItem.indexOf(CmdUtil.GET_ENDPOINT_ACK) == 0) {
//				System.out.println("singleItem:"+singleItem);
				String[] deviceCmd = singleItem.substring(CmdUtil.GET_ENDPOINT_ACK.length()).trim().split("\\\\");// '\'�ָ�
				Device currentdevice = null;
				
				if (deviceCmd != null && deviceCmd.length >= 5) {
						String name = deviceCmd[0];
						currentdevice = new Device(loopmac, StringUtils.trimToEmpty(name), deviceCmd[1], deviceCmd[2], deviceCmd[3]);
						logger.debug("currentdevice from socket:"+ currentdevice);
						updateDeviceDetails( currentdevice );
				}
			} else if (StringUtils.isNotBlank(singleItem) && singleItem.indexOf(CmdUtil.DEV_ONLINE) == 0) {
				//updateDeviceDetailsByDeviceMac(singleItem);
			} else if (StringUtils.isNotBlank(singleItem) && (singleItem.indexOf("DAT\\FINISHED") == 0 )) {

			}else if(StringUtils.isNotBlank(singleItem) && (singleItem.indexOf("DAT") == 0)){
				String[] deviceCmd = singleItem.substring(CmdUtil.DEV_DAT.length()).trim().split("-");// '-'�ָ�
				String devMac = deviceCmd[0];
				String eq =  deviceCmd[1];
				String attrId =  deviceCmd[2];
				//System.out.println("processBar:" + Integer.parseInt(deviceCmd[3],16));
				if(StringUtils.isNotBlank(devMac) && StringUtils.isNotBlank(eq)){
					stringRedisTemplate.opsForValue().set( devMac+":"+eq,Integer.parseInt(deviceCmd[3],16) + "" );
				}
			}
		}
	}

	private void updateDeviceDetails(Device device) {
		for (Device currentdevice : Constants.deviceList) {
			if (currentdevice.getEquipmentMac().equalsIgnoreCase(device.getEquipmentMac()) && currentdevice.getEquipmentEp().equalsIgnoreCase(device.getEquipmentEp()) && !currentdevice.getName().equalsIgnoreCase(device.getName()) ) {
				currentdevice.setName(device.getName());
				currentdevice.setDevid(device.getDevid());
				logger.debug("updateDeviceDetails:"+device.getName());
				SpringUtil.getUserMapper().updateHouseRelation(currentdevice);
				logger.debug("update the redis...");
				break;
			}
			//ConstantsMethod.updateDeviceLists(Constants.deviceList);
		}
	}


	private String serviceRremain = "";

	synchronized void segmentService(String data, List<String> cmdList, List<String> macList) {
		while (data.length() > 0) {
			if (data.length() >= 16 && !"7676".equals(data.substring(12, 16)) && serviceRremain != null && serviceRremain.length() > 0) {
				data = serviceRremain + data;
				serviceRremain = "";
			}
			if (data.length() < (12 + 16)) {
				serviceRremain = "";
				return;
			}
			String mac = data.substring(0, 12);
			data = data.substring(12);

			if (data.isEmpty()) {
				serviceRremain = "";
				return;
			}
			macList.add(mac);
			String a = data.substring(4, 8);
			int n = ToHexUtil.HexToInt(a) * 2 + 8 + 4;
			if (n > data.length()) {
				if ("7676".equals(data.substring(0, 4))) {
					serviceRremain = mac + data;
				} else {
					serviceRremain = "";
				}
				return;
			} else {
				cmdList.add(data.substring(0, n));
				data = data.substring(n);
			}
		}
	}

	synchronized protected String parse(String data, AesUtil mAesUtil, boolean isZw) {

		String baotou = data.substring(0, 4);
		if (!"7676".equals(baotou) || !"0D0A".equals(data.substring(data.length() - 4)) || ToHexUtil.strHexInt(data.substring(4, 8)) % 16 != 0) {
			return null;
		}
		String bao = data.substring(8, data.length() - 4);
		if (bao.length() % 16 != 0) {
			return null;
		}
		byte[] encode = mAesUtil.decrypt(ToHexUtil.hexStringToByte(bao));
		if (encode == null || encode != null && encode.length == 0) {
			return null;
		} else {
			byte[] crc = new byte[encode.length - 1];
			for (int i = 0; i < encode.length - 1; i++)
				crc[i] = encode[i];
			if (encode[encode.length - 1] != CRC8.calcCrc8_2(crc)) {
				return null;
			}

			if (crc[0] == ((byte) 0xFF)) {// δԼ����Э���
				String result = null;
				try {
					if (crc.length > 2) {// ȥǰ��λЭ��ź�userid
						byte[] msg = new byte[crc.length - 2];
						for (int i = 0; i < crc.length - 2; i++)
							msg[i] = crc[i + 2];

						result = new String(msg, "UTF-8");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return result;
			} else {
				return ToHexUtil.byte2HexStr(crc, crc.length);
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		try {
			/*super.messageSent(session, message);
			char[] mChars = "0123456789ABCDEF".toCharArray();
			byte[] bytes = (byte[]) message;
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < bytes.length; n++) {
				sb.append(mChars[(bytes[n] & 0xFF) >> 4]);
				sb.append(mChars[bytes[n] & 0x0F]);// 0x0f����ʮ���Ƶ�15
			}
			sb.toString().trim().toUpperCase(Locale.US);
			logger.debug("sb:" + sb.toString());*/
		}catch (Exception e){
			e.printStackTrace();
		}


	}

}
