package com.bean.google.newpojo.command.request; /**
  * Copyright 2018 bejson.com 
  */

import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:37:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DeviceExeRequest {

    private String requestId;
    private List<Inputs> inputs;
    public void setRequestId(String requestId) {
         this.requestId = requestId;
     }
     public String getRequestId() {
         return requestId;
     }

    public void setInputs(List<Inputs> inputs) {
         this.inputs = inputs;
     }
     public List<Inputs> getInputs() {
         return inputs;
     }

}