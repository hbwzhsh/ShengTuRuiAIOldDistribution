package com.netty.model;

import com.alibaba.fastjson.JSONObject;

public class SocketMessageUtil {
	
	
	/**
	 * 通用响应框架
	 * @param transactionId
	 * @return
	 */
	public static String getSimpleResponse(String type,int transactionId,int userId) {
		JSONObject json = new JSONObject();
		json.put("type", type);
		json.put("transid", transactionId);
		json.put("userid", userId);
		json.put("dataversion", 0);
		json.put("body", "");
		return json.toJSONString();
	}


	/**
	 * 通用响应框架
	 * @param transactionId
	 * @return
	 */
	public static String getSimpleResultResponse(String type,int transactionId,int result) {
		JSONObject json = new JSONObject();
		json.put("type", type);
		json.put("transid", transactionId);
		json.put("result", result);
		return json.toJSONString();
	}



	/**
	 * 通用响应框架
	 * @param transactionId
	 * @return
	 */
	public static String responseFormat(String typeName , int transactionId , String tempjson) {
		JSONObject json = new JSONObject();
		json.put("type", typeName );
		json.put("transid", transactionId);
		
		json.put("body","[{"+tempjson+"}]" );
		return json.toJSONString();
	}
	
	
	

	
}
