/**
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at
 * <p>
 * http://aws.amazon.com/apache2.0/
 * <p>
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.intent.amazonintent;

import com.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.dialog.directives.DelegateDirective;
import com.amazon.speech.speechlet.dialog.directives.DialogIntent;
import com.amazon.speech.speechlet.dialog.directives.DialogSlot;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;
import com.intent.amazonintent.refacting.AmazonResponse;
import com.intent.amazonintent.refacting.IntentTypeFactory;
import com.intent.amazonintent.refacting.intends.IntendRequestInterface;
import com.service.AmazonService;
import com.utility.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import java.util.*;

/**
 * This sample shows how to create a simple speechlet for handling speechlet
 * requests.
 */

@WebServlet("/HandleRequestOne")
public class HandleAmazonRequest extends SpeechletServlet {
    private static final long serialVersionUID = 1L;

    public HandleAmazonRequest() {
        this.setSpeechlet(new DeviceSpeechlet());
    }
}

class DeviceSpeechlet implements Speechlet {
    private StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        logger.debug("HelloWorldSpeechlet--->");
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        String accessToken = session.getUser().getAccessToken();

        logger.debug("accessToken:" + accessToken);

        System.out.println("accessToken:" + accessToken);


        String speechText = StringUtils.EMPTY;

        if (StringUtils.isBlank(session.getUser().getAccessToken())) {
            return AmazonResponse.getNewAskResponseForReconnecting();
        }

        UserOauth2 userTemp = new UserOauth2();
        userTemp.setAccessToken(accessToken);
        UserOauth2 user = SpringUtil.getUserMapper().getOauth2ByCondition(userTemp);

        if (user == null) {
            speechText = "I can not find your email " + accessToken + " within Smart home server , please make a contact with us.";
            logger.debug("speechText:" + speechText);
            return AmazonResponse.getNewAskResponse(speechText);
        }

        speechText = "welcome back to Smart home ,what can I do for you ?";
        return AmazonResponse.getNewAskResponse(speechText);
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {

        Intent intent = request.getIntent();
        String accessToken = session.getUser().getAccessToken();
        String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
        IntentRequest.DialogState dialogueState = request.getDialogState();

        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setAccessToken(accessToken);
        UserOauth2 result = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2);

        String speechText = StringUtils.EMPTY;
        if (result == null) {
            speechText = "I can not find your email " + accessToken + " within Smart home database ,please make a contact with us.";
            logger.debug("speechText:" + speechText);
            return AmazonResponse.getNewAskResponse(speechText);
        }

        if(dialogueState == null){
            return getSpeechletRealResponse(session, intent, accessToken, intentName, result);
        }
        //If the IntentRequest dialog state is STARTED
        if (dialogueState.equals(IntentRequest.DialogState.STARTED)) {

            DialogIntent dialogIntent = new DialogIntent();
            dialogIntent.setName(intentName);
            Map<String, DialogSlot> dialogSlots = new HashMap<String, DialogSlot>();

            Iterator iter = intent.getSlots().entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry pair = (Map.Entry) iter.next();
                DialogSlot dialogSlot = new DialogSlot();
                Slot slot = (Slot) pair.getValue();
                String name = slot.getName();
                String value = slot.getValue();
                dialogSlot.setName(name);
                dialogSlot.setValue(value);

                if (Constants.whereStr.equalsIgnoreCase(name) && StringUtils.isBlank(value)) {
                    String cacheldefaultRoom = stringRedisTemplate.opsForValue().get(Constants.dafualtRoomKey + result.getUserId());
                    logger.debug("cacheldefaultRoom:"+cacheldefaultRoom);
                    if (StringUtils.isNotBlank(cacheldefaultRoom)) {
                        dialogSlot.setName(Constants.whereStr);
                        dialogSlot.setValue(cacheldefaultRoom);
                    }
                }

                dialogSlots.put((String) pair.getKey(), dialogSlot);
            }

            dialogIntent.setSlots(dialogSlots);
            DelegateDirective dd = new DelegateDirective();
            dd.setUpdatedIntent(dialogIntent);


            List<Directive> directiveList = new ArrayList<Directive>();
            directiveList.add(dd);
            SpeechletResponse speechletResp = new SpeechletResponse();
            speechletResp.setDirectives(directiveList);
            speechletResp.setShouldEndSession(false);
            System.out.println("speechletResp:+++" + JSONObject.toJSONString(speechletResp));
            return speechletResp;
        } else if (dialogueState.equals(IntentRequest.DialogState.COMPLETED)) {
            return getSpeechletRealResponse(session, intent, accessToken, intentName, result);
        } else {
            DelegateDirective dd = new DelegateDirective();
            List<Directive> directiveList = new ArrayList<Directive>();
            directiveList.add(dd);
            SpeechletResponse speechletResp = new SpeechletResponse();
            speechletResp.setDirectives(directiveList);
            speechletResp.setShouldEndSession(false);
            return speechletResp;
        }

    }

    private SpeechletResponse getSpeechletRealResponse(Session session, Intent intent, String accessToken, String intentName, UserOauth2 userOauth2) {

        IntendRequestInterface intentObj = IntentTypeFactory.getIntentTypeByName(intentName);
        return intentObj.doSomething(intent, session, userOauth2);
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
        // log.info("onSessionEnded requestId={}, sessionId={}",
        // request.getRequestId(), session.getSessionId());
        // any cleanup logic goes here
    }

}
