package com.intent.amazonintent.refacting.intends;

import com.SpringUtil;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;
import com.intent.amazonintent.refacting.AmazonResponse;
import com.service.AmazonService;
import com.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public abstract class LightAndCurtainsAbstract implements IntendRequestInterface {

	public String voiceMessge;

	private static final String preCacheRequestData = "preCacheRequestData";

	private IntendParams item;

	private StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	private Intent intentSession;
	
	public boolean createIntendParasObject(Intent intent, Session session, UserOauth2 user) {
		
		// TODO Auto-generated method stub
		this.item = IntendParams.createIntendParamsObj(intent,session.getUser().getAccessToken());
		this.item.setUserId(user.getUserId());

		if (StringUtils.isBlank(this.item.getWhere())) {
			String cacheldefaultRoom = stringRedisTemplate.opsForValue().get(Constants.dafualtRoomKey + this.item.getUserId());
			logger.debug("cacheldefaultRoom:"+cacheldefaultRoom);
			if (StringUtils.isNotBlank(cacheldefaultRoom)) {
				this.item.setWhere(cacheldefaultRoom);
			}
		}

		logger.debug("where------------>:"+this.item.getWhere());
		logger.debug("devicename------->:"+this.item.getDevicename());
		logger.debug("cmd:"+this.item.getDeviceCMD());
		logger.debug("userId------------>:"+this.item.getUserId());
		logger.debug("intentname-------------->:"+this.item.getIntentName());
		logger.debug("persentage-------------->:"+this.item.getPersentage());
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
	public SpeechletResponse doSomething(Intent intend, Session session, UserOauth2 user) {
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
