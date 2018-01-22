/**
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at
 * <p>
 * http://aws.amazon.com/apache2.0/
 * <p>
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.amazonintent;

import java.util.LinkedHashMap;

import javax.servlet.annotation.WebServlet;

import com.amazon.speech.speechlet.*;
import com.controller.WelcomeController;
import com.init.AmazonService;
import com.init.HttpServiceClient;
import com.intends.IntendRequestInterface;
import com.intends.util.AmazonResponse;
import com.intends.util.IntentTypeFactory;
import org.apache.commons.lang3.StringUtils;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/HandleRequestOne")
public class HandleAmazonRequest extends SpeechletServlet {
    private static final long serialVersionUID = 1L;

    public HandleAmazonRequest() {
        this.setSpeechlet(new DeviceSpeechlet());
    }
}

class DeviceSpeechlet implements Speechlet {
    private static Logger logger = LoggerFactory.getLogger(DeviceSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
        logger.debug("HelloWorldSpeechlet--->");
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
        String userEmail = AmazonService.getProfileData(session.getUser().getAccessToken());
        String speechText = StringUtils.EMPTY;
        if (StringUtils.isBlank(session.getUser().getAccessToken())) {
            return AmazonResponse.getNewAskResponseForReconnecting();
        }
        com.model.User user = new HttpServiceClient().realIp(userEmail);

        if (user == null) {
            speechText = userUnfoundErrorMessage(user.getEmail());
            return AmazonResponse.getNewAskResponse(speechText);
        }
        speechText = "welcome back to Smart plus Devices,what can I do for you ?";
        return AmazonResponse.getNewAskResponse(speechText);
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
        Intent intent = request.getIntent();
        String userEmail = AmazonService.getProfileData(session.getUser().getAccessToken());
        String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
        String speechText = StringUtils.EMPTY;

        if (StringUtils.isBlank(userEmail)) {
            return AmazonResponse.getNewAskResponseForReconnecting();
        }

        com.model.User user = new HttpServiceClient().realIp(userEmail);
        if (user == null) {
            speechText = userUnfoundErrorMessage(user.getEmail());
            return AmazonResponse.getNewAskResponse(speechText);
        }

        Object obj = session.getAttribute("where");
        if (obj != null) {
            LinkedHashMap objitem = (LinkedHashMap) session.getAttribute("currentList");
            if (objitem.containsKey("intentName")) {
                intentName = objitem.get("intentName").toString();
                logger.debug("objitem.get().toString()-------<>");
            }
        }
        IntendRequestInterface intentObj = IntentTypeFactory.getIntentTypeByName(intentName);
        return intentObj.doSomething(intent, session, user);
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
        // log.info("onSessionEnded requestId={}, sessionId={}",
        // request.getRequestId(), session.getSessionId());
        // any cleanup logic goes here
    }

    private String userUnfoundErrorMessage(String userEmail) {
        return "I can not find your email " + userEmail + " within Smart plus database ,please make a contact with us.";
    }

}
