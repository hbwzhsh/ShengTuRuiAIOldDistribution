package com.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bean.Device;
import com.utility.Constants;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RedisDAO {

    private static String redisStr = "redisStr";
    private static Jedis jedis = null;


    public static Jedis getRedisConnection() {

        if (jedis == null) {
            RedisDAO.jedis = new Jedis(Constants.redisIpConnectSocket, Integer.parseInt(Constants.redisPort));
            jedis.auth("123456");
        }
        return jedis;
    }


    public static void saveString(String key, String value) {
        getRedisConnection().set(key + redisStr, value);
    }

    public static String getString(String key) {
        return getRedisConnection().get(key + redisStr);
    }

    public static void saveObject(String key, Object obj) {
        try {
            getRedisConnection().set(key + redisStr, JSON.toJSON(obj).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteObject(String key) {
        try {
            getRedisConnection().del(key + redisStr);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public static HashMap getHashMap(String key) {
        String json = getRedisConnection().get(key + redisStr);
        return JSON.parseObject(json, HashMap.class);
    }


    public static List<Device> getObject(String key) {
        try {
            if (getRedisConnection().exists(key + redisStr)) {
                String json = getRedisConnection().get(key + redisStr);
                List<Device> list = JSONArray.parseArray(json, Device.class);
                List<Device> devices = new ArrayList<>();
                devices.addAll(list);
                return devices;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
