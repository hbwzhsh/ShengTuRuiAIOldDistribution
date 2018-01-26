package com.intent.amazonintent.refacting.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;
import com.data.RedisDAO;
import com.utility.Constants;
import com.service.DeviceService;
import com.intent.amazonintent.refacting.AmazonResponse;
import com.service.AmazonService;
import org.apache.commons.lang3.StringUtils;

public class CommandToolsIntend implements IntendRequestInterface {

    private DeviceService deviceService = new DeviceService();

    @Override
    public SpeechletResponse doSomething(Intent intent, Session session, UserOauth2 user) {

        String accessToken = AmazonService.getProfileData(session.getUser().getAccessToken());
        String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
        switch (intentName) {
            case Constants.SETDEFAULTROOM:
                return setDefaultRoom(intent, accessToken);
            case Constants.GETDEFAULTROOM:
                String cacheldefaultRoom = Constants.defualtRooms.get(accessToken);
                if (StringUtils.isBlank(cacheldefaultRoom)) {
                    return AmazonResponse.getNewAskResponse("you haven't set your default room.");
                } else {
                    return AmazonResponse.getNewAskResponse("the default room is your " + cacheldefaultRoom);
                }
        }
        return null;
    }


    private SpeechletResponse setDefaultRoom(Intent intent, String accessToken) {
        String defaultRoom = intent.getSlot("defaultRoom").getValue();
        if (defaultRoom != null) {

            Constants.defualtRooms.remove("accessToken");
            Constants.defualtRooms.put(accessToken, defaultRoom);

            RedisDAO.saveObject(Constants.dafualtRoomKey, Constants.defualtRooms);

            String speechText = "already set " + defaultRoom + " as your default room.";
            return AmazonResponse.getNewAskResponse(speechText);
        }

        String speechText = "sorry,I can not hear you clearly,Could you say it again?";
        return AmazonResponse.getNewAskResponse(speechText);
    }
}
