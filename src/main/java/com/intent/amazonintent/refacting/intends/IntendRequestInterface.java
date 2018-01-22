package com.intent.amazonintent.refacting.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;

public interface IntendRequestInterface {
	
	
	public SpeechletResponse doSomething(Intent intend, Session session);

	
	

}
