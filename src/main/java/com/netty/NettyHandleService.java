package com.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.controller.WelcomeController;
import com.init.Constants;
import com.init.HttpServiceClient;
import com.netty.model.*;
import com.utility.AesUtilTwo;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NettyHandleService {

    private static final Random random = new Random();

    private static Logger logger = LoggerFactory.getLogger(NettyHandleService.class);


    @RequestType(methodName = "endpint_attribute")
    public void Method2(Channel channel,  SocketMessageFormatReceive obj, Object msg) {
        String hostMac = obj.getFrom();
        List<EndPointAttribute> endPointAttributeList = JSONArray.parseArray(obj.getBody().toString(), EndPointAttribute.class);
        for (EndPointAttribute item : endPointAttributeList) {
            List<Device> deviceList = (ArrayList)Constants.logicDeviceList.get(obj.getUserid()+"");
                for (Device device :deviceList) {
                    if (device.getDeviceMac().equalsIgnoreCase(item.getMac())) {
                        for(IdValue value:device.getAttrlist()){
                            for(IdValue temp:item.getAttr()){
                                if(value.getId() == temp.getId()){
                                    value.setValue(temp.getValue());
                                }
                            }
                        }
                        device.setIsonline(true);
                    }
                }
        }
    }

    @RequestType(methodName = "device_description")
    public void Method4(Channel channel, SocketMessageFormatReceive obj, Object msg) {
        List<DeviceDescription> deviceDescription = JSONArray.parseArray(obj.getBody().toString(), DeviceDescription.class);
        String hostMac = obj.getFrom();
        List<Device> deviceList = new ArrayList<>();
        //放缓存一份同时
        for (DeviceDescription item : deviceDescription) {

            for (int i = 0; i < item.getEndpoint().size(); i++) {
                Endpoint end = item.getEndpoint().get(i);
                Device device = new Device(-1);
                device.setAttrlist(end.getAttrlist());
                device.setCmdlist(end.getCmdlist());
                device.setCustomName(end.getCustomName());
                device.setDevid(end.getDevid());
                device.setDeviceType(2);
                device.setCmdlist(end.getCmdlist());
                device.setAttrlist(end.getAttrlist());
                device.setDeviceName(end.getCustomName());
                device.setRoomId(end.getRoomId());
                device.setDeviceMac(end.getMac());
                deviceList.add(device);
                logger.debug("obj.getToken():"+device.getCustomName());
            }
        }
        logger.debug("obj.getUserid():"+obj.getUserid());
        logger.debug("obj.getToken():"+obj.getToken());
        HttpServiceClient.updateDeviceListRoomData(obj.getUserid(),obj.getToken(),deviceList);
        Constants.logicDeviceList.put(obj.getUserid(), deviceList);
    }

    @RequestType(methodName = "command_ack")
    public void Method15(Channel channel, AesUtilTwo AES, SocketMessageFormatReceive obj, Object msg) {
        String hostMac = obj.getFrom();

    }






    @RequestType(methodName = "scene_execute")
    public void Method11(Channel channel,  SocketMessageFormatReceive obj, Object msg) {
        //sendMsgToClientChannel(msg, obj.getFrom(), AES);
    }

    @RequestType(methodName = "heartbeat")
    public void Method12(Channel channel,  SocketMessageFormatReceive obj, Object msg) {
        int userId = 0;
        String tempMsg = SocketMessageUtil.getSimpleResponse("heartbeat", obj.getTransid(),userId);
        channel.writeAndFlush(NettyConstants.addLinSeparatorStringEncode( tempMsg));
    }





    private String getRemoteIp(Channel channel) {
        InetSocketAddress req = (InetSocketAddress) channel.remoteAddress();
        return req.getAddress().getHostAddress();
    }

    private SocketMessageFormatReceive parseStr(String json, AesUtilTwo AES) {
        String tempJson = AES.decode(json);
        //System.out.println("decription:" + tempJson);
        return JSON.parseObject(tempJson, SocketMessageFormatReceive.class);
    }

}
