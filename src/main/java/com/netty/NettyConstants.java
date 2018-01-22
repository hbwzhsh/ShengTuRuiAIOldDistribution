package com.netty;

import com.utility.AesUtilTwo;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class NettyConstants {

	public static Map<String,Channel> clientChannels  = new Hashtable<String,Channel>();
	
	public static Map<String,Channel> hostChannels  = new Hashtable<String,Channel>();
	public static Map<String,Object> hostDeviceList = new Hashtable<String,Object>();

	public static Map<String,HashMap<String,Channel>> clientChannelForSingleLogin = new Hashtable<String,HashMap<String,Channel>>();
	public static Map<String,Object> aesList = new Hashtable<>();
	public static Map<String,Object> deviceDataVersion = new Hashtable<>();

	public static AesUtilTwo AES = new AesUtilTwo();
	private static final String userDeviceListPreKey="userDeviceListPreKey";


	public static String addLinSeparatorStringEncode( Object msg) {
		return AES.encode(msg.toString()) + System.getProperty("line.separator");
	}


}
