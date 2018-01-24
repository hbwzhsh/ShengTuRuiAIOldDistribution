package com.intent.amazonintent.refacting.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.bean.site.UserSite;

public interface IntendRequestInterface {
	
	
	public SpeechletResponse doSomething(Intent intend, Session session, UserSite user);

	
	

}
