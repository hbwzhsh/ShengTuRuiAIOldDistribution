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
public class PayloadResponse {

    private List<CommandsResponse> commands;
    public void setCommands(List<CommandsResponse> commands) {
         this.commands = commands;
     }
    public List<CommandsResponse> getCommands() {
         return commands;
     }

}