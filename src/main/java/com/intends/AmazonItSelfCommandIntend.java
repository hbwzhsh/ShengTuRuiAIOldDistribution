package com.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.init.Constants;
import com.intends.util.AmazonResponse;
import com.model.User;
import org.apache.commons.lang3.StringUtils;

public class AmazonItSelfCommandIntend implements IntendRequestInterface {


	@Override
	public SpeechletResponse doSomething(Intent intent, Session session, User user) {

		String intentName = (intent != null) ? intent.getName().toLowerCase() : null;
		
		switch (intentName) {
			case Constants.HELPINTENT:
                 return helpIntendResponse();           
			case Constants.CANCELINTENT:
				AmazonResponse.newTellResponse();
				break;
			case Constants.STOPINTENT:
				return AmazonResponse.newTellResponse(StringUtils.EMPTY);
			}
		return null;
	}
	
	private SpeechletResponse helpIntendResponse(){
		return AmazonResponse.getNewAskResponse(Constants.helpContent);
	}

	
}
