package com.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.init.Constants;
import com.init.DeviceService;
import com.intends.util.AmazonResponse;
import com.model.User;
import com.redis.RedisDAO;
import org.apache.commons.lang3.StringUtils;


public class CommandToolsIntend implements IntendRequestInterface {
	DeviceService deviceService  = new DeviceService();

	@Override
	public SpeechletResponse doSomething(Intent intent , Session session, User user) {
		
		String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
		switch (intentName) {
			case Constants.SETDEFAULTROOM:
				return setDefaultRoom(intent, user.getUserId());
			case Constants.REFRESHDATA:
				return refreshData( user.getUserId(), user.getToken());
			case Constants.GETDEFAULTROOM:
				String cacheldefaultRoom = Constants.defualtRooms.get(user.getUserId());
				if (StringUtils.isBlank(cacheldefaultRoom)) {
					return AmazonResponse.getNewAskResponse("you haven't set your default room.");
				} else {
					return AmazonResponse.getNewAskResponse("the default room is your " + cacheldefaultRoom);
				}
			}
		return null;
	}

	private SpeechletResponse refreshData(String userId,String token) {
		boolean exeFlag = deviceService.refreshCacheData(userId,token);
		String speechText = StringUtils.EMPTY;
		if (!exeFlag) {
			speechText = "you need to go to alexa app to link your account again!";
		} else {
			speechText = "OK,I am working on that,please try it later.";
		}
		return AmazonResponse.getNewAskResponse(speechText);
	}

	private SpeechletResponse setDefaultRoom(Intent intent, String userId) {
		String defaultRoom = intent.getSlot("defaultRoom").getValue();
		if (defaultRoom != null) {

			Constants.defualtRooms.remove("accessToken");
			Constants.defualtRooms.put(userId, defaultRoom);

			RedisDAO.saveObject(Constants.dafualtRoomKey, Constants.defualtRooms);

			String speechText = "already set " + defaultRoom + " as your default room.";
			return AmazonResponse.getNewAskResponse(speechText);
		}

		String speechText = "sorry,I can not hear you clearly,Could you say it again?";
		return AmazonResponse.getNewAskResponse(speechText);
	}
}
