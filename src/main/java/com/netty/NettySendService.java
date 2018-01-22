package com.netty;

import com.alibaba.fastjson.JSONObject;
import com.netty.model.DeviceCommand;
import com.netty.model.SocketMessageFormatResponse;
import com.netty.model.SocketMessageUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

public class NettySendService {

    public static Channel channel = null;
    private static Logger logger = LoggerFactory.getLogger(NettySendService.class);

    public void newConnection(int userId) {
        String sendJson = SocketMessageUtil.getSimpleResponse("newclientconnection", 52314, userId);
        logger.debug("newConnection：" + sendJson);
        channel.writeAndFlush(NettyConstants.addLinSeparatorStringEncode(sendJson));
    }

    public void clientgetallDescription(int userId) {
        String sendJson = SocketMessageUtil.getSimpleResponse("clientgetall_description", 52314, userId);
        logger.debug("clientgetallDescription：" + sendJson);
        channel.writeAndFlush(NettyConstants.addLinSeparatorStringEncode(sendJson));
    }

    public void newCommand(String userId, String deviceMac, int op, int cmdId) {
        SocketMessageFormatResponse response = new SocketMessageFormatResponse();
        response.setUserid(userId);
        DeviceCommand cmd = new DeviceCommand();
        cmd.setCmdid(cmdId);
        List<String> oplist = new ArrayList();
        oplist.add(op + "");
        cmd.setOp(oplist);
        cmd.setMac(deviceMac);
        response.setBody(cmd);
        String sendJson = JSONObject.toJSONString(response);
        logger.debug("newCommand：" + sendJson);
        channel.writeAndFlush(NettyConstants.addLinSeparatorStringEncode(sendJson));
    }

    private void heartBeat(int userId) {
        String sendJson = SocketMessageUtil.getSimpleResponse("heartbeat", 52314, userId);
        channel.writeAndFlush(NettyConstants.addLinSeparatorStringEncode(sendJson));
    }

}
