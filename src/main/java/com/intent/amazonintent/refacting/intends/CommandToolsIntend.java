package com.intent.amazonintent.refacting.intends;

import com.SpringUtil;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.bean.site.UserOauth2;
import com.utility.Constants;
import com.service.DeviceService;
import com.intent.amazonintent.refacting.AmazonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

public class CommandToolsIntend implements IntendRequestInterface {

    private DeviceService deviceService = new DeviceService();

    private  StringRedisTemplate stringRedisTemplate =(StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    @Override
    public SpeechletResponse doSomething(Intent intent, Session session, UserOauth2 user) {
        String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
        switch (intentName) {
            case Constants.SETDEFAULTROOM:
                return setDefaultRoom(intent, user.getUserId());
            case Constants.GETDEFAULTROOM:
                String cacheldefaultRoom = stringRedisTemplate.opsForValue().get(Constants.dafualtRoomKey+user.getUserId());
                if (StringUtils.isBlank(cacheldefaultRoom)) {
                    return AmazonResponse.getNewAskResponse("you haven't set your default room.");
                } else {
                    return AmazonResponse.getNewAskResponse("the default room is your " + cacheldefaultRoom);
                }
        }
        return null;
    }

    private SpeechletResponse setDefaultRoom(Intent intent, String userId) {
        String defaultRoom = intent.getSlot("defaultRoom").getValue();
        if (defaultRoom != null) {
            stringRedisTemplate.opsForValue().set(Constants.dafualtRoomKey + userId, defaultRoom);
            String speechText = "already set " + defaultRoom + " as your default room.";
            return AmazonResponse.getNewAskResponse(speechText);
        }

        String speechText = "sorry,I can not hear you clearly,Could you say it again?";
        return AmazonResponse.getNewAskResponse(speechText);
    }
}
