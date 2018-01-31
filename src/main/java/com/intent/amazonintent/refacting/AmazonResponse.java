package com.intent.amazonintent.refacting;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.dialog.directives.ElicitSlotDirective;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class AmazonResponse {

	
	/**
	 * Creates and returns a {@code SpeechletResponse} with a welcome message.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	public static SpeechletResponse getNewAskResponse(String responseText) {


		ElicitSlotDirective slotDirective = new ElicitSlotDirective();
		slotDirective.



		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("managerDevice");
		card.setContent(responseText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(responseText);
		
		
		PlainTextOutputSpeech speech1 = new PlainTextOutputSpeech();
		speech1.setText("");

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech1);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}
	
	public static SpeechletResponse getNewAskResponseForReconnecting() {
		 String speechText = "your echo losted connection with system, you need to go to alexa app to link your account again!";
		 return AmazonResponse.getNewAskResponse(speechText);
	}

	/**
	 * Creates a {@code SpeechletResponse} for the hello intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	public static SpeechletResponse newTellResponse() {
		String speechText = "OK!";
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("managerDevice");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);
		return SpeechletResponse.newTellResponse(speech, card);
	}
	public static SpeechletResponse newTellResponse(String str) {
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("managerDevice");
		card.setContent(str);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(str);
		return SpeechletResponse.newTellResponse(speech, card);
	}
}
