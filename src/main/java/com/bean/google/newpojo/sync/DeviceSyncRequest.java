package com.bean.google.newpojo.sync; /**
  * Copyright 2018 bejson.com 
  */

import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:13:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DeviceSyncRequest {

    private String requestId;
    private List<InputsSync> inputs;
    public void setRequestId(String requestId) {
         this.requestId = requestId;
     }
     public String getRequestId() {
         return requestId;
     }

    public void setInputs(List<InputsSync> inputs) {
         this.inputs = inputs;
     }
     public List<InputsSync> getInputs() {
         return inputs;
     }

}