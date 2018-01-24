package com.utility;

import java.util.Random;
import java.util.UUID;

public class TokenFactory {

    public static final String client="0Iafg3rasL9gnivV";
    public static final String secret="SBB7VWn83hbHI5kt";
    public static final int expires_in = 36000;

    public static String createRefreshToken(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<3;i++){
            builder.append(random.nextInt(10));
        }
        return client+builder.toString();
    }

    public static String createAccessToken(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<3;i++){
            builder.append(random.nextInt(10));
        }
        return secret+builder.toString();
    }

    public static String createCode(){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }
}
