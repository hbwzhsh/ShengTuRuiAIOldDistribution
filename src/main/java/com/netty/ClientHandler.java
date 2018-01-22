package com.netty;

import com.alibaba.fastjson.JSON;
import com.intends.LightAndCurtainsAbstract;
import com.netty.model.RequestType;
import com.netty.model.SocketMessageFormatReceive;
import com.redis.RedisDAO;
import com.utility.AesUtilTwo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Random;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Random random = new Random();


    private static final Logger logger = LogManager.getLogger( ClientHandler.class );


    public ClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("netty actvice");
        NettySendService.channel = ctx.channel();
      /*  RedisDAO redisDAO = new RedisDAO();
        redisDAO.saveString("123","123");*/

        new NettySendService().newConnection(10154);
        new NettySendService().clientgetallDescription(10154);

    }


    @SuppressWarnings("unchecked")
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            System.out.println("something returned:"+msg);
            SocketMessageFormatReceive obj = parseStr(msg.toString());
            String hostMac = obj.getFrom();

            NettyHandleService nettyService = new NettyHandleService();
            Method[] methods = NettyHandleService.class.getDeclaredMethods();
            for (Method method : methods) {
                RequestType requestTypes = method.getDeclaredAnnotation(RequestType.class);
                if (requestTypes == null) {
                    continue;
                }
                if (requestTypes.methodName() != null & requestTypes.methodName().equalsIgnoreCase(obj.getType())) {
                    method.invoke(nettyService, ctx.channel(), obj, obj.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private SocketMessageFormatReceive parseStr(String json) {
        String tempJson = NettyConstants.AES.decode(json);
        System.out.println("receive from host:" + tempJson);
        SocketMessageFormatReceive receive = JSON.parseObject(tempJson, SocketMessageFormatReceive.class);
        receive.setMsg(tempJson);
        return receive;
    }


}
