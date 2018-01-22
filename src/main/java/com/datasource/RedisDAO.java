package com.datasource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bean.Device;
import com.init.Constants;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RedisDAO {
	
   private static Jedis jedis=null;
   
   
   
   public static Jedis getRedisConnection() {
	   
	   if(jedis == null) {
		   RedisDAO.jedis = new Jedis(Constants.redisIpConnectSocket, Integer.parseInt(Constants.redisPort));
		   jedis.auth("123456");
	   }
	   
	   return jedis;
   }
   
   
   
   public static void saveString( String key , String value ){
	   getRedisConnection().set(key, value);
   }
   
   public static String getString(String key){
	  return  getRedisConnection().get(key);
   }
   
   public static void saveObject(String key,Object obj){
	   getRedisConnection().set(key, JSON.toJSON(obj).toString());
   }
   
   
   public static void deleteObject(String key){
	   getRedisConnection().del(key);
   }
   
   
   public static HashMap getHashMap(String key){
	   String json = getRedisConnection().get( key );
	   return JSON.parseObject(json, HashMap.class);
   }
   
   
   public static HashSet<Device> getHashSet(String key ){
	   if(getRedisConnection().exists(key)){
		   String json = getRedisConnection().get( key );
		   List<Device> list = JSONArray.parseArray(json, Device.class);
		   HashSet<Device> devices = new HashSet<Device>();
		   devices.addAll(list);
		   return devices;
	   }
	   return new HashSet<Device>();
   }

}
