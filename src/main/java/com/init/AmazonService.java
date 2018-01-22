package com.init;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

public class AmazonService {
	
	public static String  getProfileData( String access_token ){
		try{
			org.apache.http.client.fluent.Response response =  Request.Get("https://api.amazon.com/user/profile").addHeader("Authorization", "bearer " + access_token.trim()).execute();
			Content c = response.returnContent();
			return JSONObject.parseObject(c.toString()).get("email").toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return StringUtils.EMPTY;		 
	}

	
    public static String   getAccesstoken( String code ){
		
		try{
			Content c  = Request.Post("https://api.amazon.com/auth/o2/token").addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
					.bodyForm(Form.form().add("token_type", "bearer").add("grant_type", "authorization_code").add("code", code).add("client_id", Config.clientId).add("client_secret", Config.clientSecret)
					.build()).execute().returnContent();
			return JSONObject.parseObject(c.toString()).get("access_token").toString();
		}catch(Exception e){
			e.printStackTrace();
		}
        return StringUtils.EMPTY;		
	}
    
    public static String getAmazonUserId(String code){
        return getProfileData(getAccesstoken(code));
    }

}
