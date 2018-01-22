package com.intends;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.model.User;

public interface IntendRequestInterface {

	public SpeechletResponse doSomething(Intent intend, Session session, User user);

}
