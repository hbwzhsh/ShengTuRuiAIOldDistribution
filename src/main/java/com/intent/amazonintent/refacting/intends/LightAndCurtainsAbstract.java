package com.intent.amazonintent.refacting.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.User;
import com.bean.site.UserSite;
import com.intent.amazonintent.refacting.AmazonResponse;
import com.service.AmazonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class LightAndCurtainsAbstract implements IntendRequestInterface {

	public String voiceMessge;

	private IntendParams item;
	
	private static final Logger logger = LogManager.getLogger(LightAndCurtainsAbstract.class);

	private Intent intentSession;
	
	public boolean createIntendParasObject(Intent intent, Session session, UserSite user) {
		
		// TODO Auto-generated method stub
		this.item = IntendParams.createIntendParamsObj(intent, AmazonService.getProfileData(session.getUser().getAccessToken()));
		Object obj = session.getAttribute("where");
		if (null != obj) {

			@SuppressWarnings("rawtypes")
			LinkedHashMap objitem = (LinkedHashMap) session.getAttribute("currentList");
			logger.debug(objitem.keySet().toArray() + "");
			IntendParams tempItem = new IntendParams();

			if (objitem.containsKey("devicename")) {
				tempItem.setDevicename((String) objitem.get("devicename"));
			}

			if (objitem.containsKey("persentage")) {
				tempItem.setPersentage((String) objitem.get("persentage"));
			}

			if (objitem.containsKey("accessToken")) {
				tempItem.setAccessToken((String) objitem.get("accessToken"));
			}

			if (objitem.containsKey("intentName")) {
				tempItem.setIntentName((String) objitem.get("intentName"));
			}

			if (objitem.containsKey("deviceState")) {
				tempItem.setDeviceState((String) objitem.get("deviceState"));
			}

			if (objitem.containsKey("deviceCMD")) {
				tempItem.setDeviceCMD((String) objitem.get("deviceCMD"));
			}

			tempItem.setWhere(this.item.getWhere());
			this.item.setUserId(user.getUserId());
			setIntentSession(intent);
			
			this.item = tempItem;
		}

		if (StringUtils.isBlank(item.getWhere())) {

			session.setAttribute("currentList", item);
			IntendParams paras = (IntendParams) session.getAttribute("currentList");
			
			logger.debug(paras.getIntentName() + "..........");
			session.setAttribute("where", "");

			voiceMessge = ("tell me which room that you want to control");
			return false;
		}
		session.removeAttribute("where");
		session.removeAttribute("currentList");
		return true;
	}

	abstract void sendCmdToServer(List<Device> filterlist);

	abstract List<Device> findDevices();

	public IntendParams getItem() {
		return item;
	}
	
	

	public Intent getIntentSession() {
		return intentSession;
	}

	public void setIntentSession(Intent intentSession) {
		this.intentSession = intentSession;
	}

	public void setItem(IntendParams item) {
		this.item = item;
	}

	@Override
	public SpeechletResponse doSomething(Intent intend, Session session, UserSite user) {
		// TODO Auto-generated method stub
		boolean flag = createIntendParasObject(intend, session,user);
		if (!flag) {
			return AmazonResponse.getNewAskResponse(voiceMessge);
		}

		logger.debug("getDeviceCMD:" + getItem().getDeviceCMD() + "--->getPersentage:" + getItem().getPersentage() + "-->getIntentName:" + getItem().getIntentName() + "-->getDeviceState:" + getItem().getDeviceState());
		logger.debug("getDevicename:" + getItem().getDevicename());
		logger.debug("where:" + getItem().getWhere());

		final List<Device> filterlist = findDevices();
		logger.debug("tempDevicelist-->" + filterlist.size());

		if (filterlist.size() == 0) {
			String speechText = "I can not find any devices in your" + getItem().getWhere() + ", please try to say download the data from server !";
			return AmazonResponse.getNewAskResponse(speechText);
		}
		sendCmdToServer(filterlist);

		return AmazonResponse.getNewAskResponse("OK");
	}

}
