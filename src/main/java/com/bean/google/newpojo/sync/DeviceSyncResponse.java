package com.bean.google.newpojo.sync; /**
  * Copyright 2018 bejson.com 
  */

/**
 * Auto-generated: 2018-01-24 10:27:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DeviceSyncResponse {

    private String requestId;
    private PayloadSync payload;
    public void setRequestId(String requestId) {
         this.requestId = requestId;
     }
     public String getRequestId() {
         return requestId;
     }

    public void setPayload(PayloadSync payload) {
         this.payload = payload;
     }
     public PayloadSync getPayload() {
         return payload;
     }

}