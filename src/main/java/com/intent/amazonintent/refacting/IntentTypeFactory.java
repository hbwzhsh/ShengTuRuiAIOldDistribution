package com.intent.amazonintent.refacting;

import com.utility.Constants;
import com.intent.amazonintent.refacting.intends.*;

import java.util.Arrays;
import java.util.List;

public class IntentTypeFactory {
	
	private final static List<String> bodyCmds = Arrays.asList(Constants.OPENCURTAINS,Constants.CLOSECURTAINS,Constants.TURNOFFLIGHT,Constants.TURNONLIGHT,Constants.DIMLIGHT);
	
	private final static List<String> headCmds = Arrays.asList(Constants.SETDEFAULTROOM, Constants.REFRESHDATA, Constants.GETDEFAULTROOM);
	
	private final static List<String> systemCmds = Arrays.asList(Constants.HELPINTENT, Constants.STOPINTENT, Constants.CANCELINTENT);
	
	private final static List<String> openAlittleCurtainsCmds = Arrays.asList(Constants.OPENCURTAINALITTLE,Constants.CLOSECURTAINALITTLE,Constants.OPENTHECURTAINHALFWAY);
	
	
	
	public static IntendRequestInterface getIntentTypeByName(String intentName){
		
		if(bodyCmds.contains( intentName )){
			return new LightsAndCurtainsIntend();
		}
		
		if(headCmds.contains( intentName )){
			return new CommandToolsIntend();
		}
		
		if(systemCmds.contains( intentName )){
			return new AmazonItSelfCommandIntend();
		}
		
		if(openAlittleCurtainsCmds.contains(intentName)){
			return new PercentCurtainsIntend();
		}
		
		return null;
	}

}
