package com.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class Config {

    @Value("${currentServerPath}")
    public  String currentServerPath;

    public String getCurrentServerPath() {
        return currentServerPath;
    }

    public void setCurrentServerPath(String currentServerPath) {
        this.currentServerPath = currentServerPath;
    }
}
