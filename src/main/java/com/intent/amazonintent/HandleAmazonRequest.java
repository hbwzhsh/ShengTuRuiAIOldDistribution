/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.intent.amazonintent;

import com.SpringUtil;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.bean.site.UserSite;
import com.intent.amazonintent.refacting.AmazonResponse;
import com.intent.amazonintent.refacting.IntentTypeFactory;
import com.intent.amazonintent.refacting.intends.IntendRequestInterface;
import com.service.AmazonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import java.util.LinkedHashMap;

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
@Component
class DeviceSpeechlet implements Speechlet {
	private static final Logger logger = LogManager.getLogger(DeviceSpeechlet.class);

	@Override
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		logger.debug("HelloWorldSpeechlet--->");
	}

	@Override
	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		
		String accessToken = AmazonService.getProfileData(session.getUser().getAccessToken());
		
		logger.debug("accessToken:"+accessToken);
		
		String speechText = StringUtils.EMPTY;
		
		if(StringUtils.isBlank(session.getUser().getAccessToken())){
			return AmazonResponse.getNewAskResponseForReconnecting();
		}

		UserSite userTemp = new UserSite();
		userTemp.setEmail(accessToken);
		UserSite user = SpringUtil.getUserMapper().getObjectByCondition(userTemp);
		if(user == null){
			speechText = "I can not find your email "+accessToken+" within Smart plus server , please make a contact with us.";
			logger.debug("speechText:"+speechText);
			return AmazonResponse.getNewAskResponse( speechText );
		}
			
		speechText = "welcome back to Smart plus DevicesSync,what can I do for you ?";
		return AmazonResponse.getNewAskResponse( speechText );
	}

	@Override
	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {

		Intent intent = request.getIntent();
		String accessToken = AmazonService.getProfileData(session.getUser().getAccessToken());
		String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
		
		logger.debug("accessToken:"+accessToken);
		
		String speechText = StringUtils.EMPTY;
		
		if(StringUtils.isBlank(accessToken)){
			return AmazonResponse.getNewAskResponseForReconnecting();
		}


		UserSite userTemp = new UserSite();
		userTemp.setEmail(accessToken);
		UserSite user = SpringUtil.getUserMapper().getObjectByCondition(userTemp);
		if(user == null){
			speechText = "I can not find your email "+accessToken+" within Smart plus database ,please make a contact with us.";
			logger.debug("speechText:"+speechText);
			return AmazonResponse.getNewAskResponse( speechText );
		}
		
		//�ж��Ƿ���dialogģʽ
		Object obj = session.getAttribute("where");
		if(obj != null){
			@SuppressWarnings("rawtypes")
			LinkedHashMap objitem = (LinkedHashMap) session.getAttribute("currentList");
			if(objitem.containsKey("intentName")){
				intentName = objitem.get("intentName").toString();
				logger.debug("objitem.get().toString()-------<>");
			}
		}
		
		IntendRequestInterface intentObj = IntentTypeFactory.getIntentTypeByName(intentName);
		return intentObj.doSomething(intent,session,user);

	}

	@Override
	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		// log.info("onSessionEnded requestId={}, sessionId={}",
		// request.getRequestId(), session.getSessionId());
		// any cleanup logic goes here
	}

}
