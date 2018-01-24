package com.bean.google.newpojo.sync; /**
  * Copyright 2018 bejson.com 
  */
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2018-01-24 10:27:10
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class NameSync {

    private List<String> defaultNames;
    private String name;
    private List<String> nicknames;

    public List<String> getDefaultNames() {
        return defaultNames;
    }

    public void setDefaultNames(List<String> defaultNames) {
        this.defaultNames = defaultNames;
    }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setNicknames(List<String> nicknames) {
         this.nicknames = nicknames;
     }
     public List<String> getNicknames() {
         return nicknames;
     }

}