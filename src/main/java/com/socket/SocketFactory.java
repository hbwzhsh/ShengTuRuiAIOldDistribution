package com.socket;

import com.utility.AesUtil;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class SocketFactory {

    public static Map<String,SoketClient> socketConnections  = new Hashtable<String,SoketClient>();

    public static Map<String,AesUtil> AesConnections  = new Hashtable<String,AesUtil>();


    public static AesUtil getCurAesInstance(String sessionId) {
        return AesConnections.get(sessionId);
    }


}
