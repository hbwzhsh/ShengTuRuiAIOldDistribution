package com.bean.google.newpojo.command.response; /**
  * Copyright 2018 bejson.com 
  */

import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:38:34
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class CommandsResponse {

    private List<String> ids;
    private String status;
    private StatesResponse states;

    public void setIds(List<String> ids) {
         this.ids = ids;
     }
     public List<String> getIds() {
         return ids;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setStates(StatesResponse states) {
         this.states = states;
     }
     public StatesResponse getStates() {
         return states;
     }

}