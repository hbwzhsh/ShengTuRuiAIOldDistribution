package com.netty;

import com.init.Config;
import com.init.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

    public NettyClient() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
//	        pipeline.addLast(new TimeStampEncoder()); // add without name, name auto generated
//	        pipeline.addLast(new TimeStampDecoder()); // add without name, name auto generated
                //===========================================================
                //    2. run handler with slow business logic
                //    in separate thread from I/O thread
                //===========================================================
                pipeline.addLast(new LineBasedFrameDecoder(9024));
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("serverHandler", new ClientHandler());
            }
        });
        b.connect( Config.serverIpConnectSocket, Config.socketPort );
    }

    public static void main(String[] args) {
        new NettyClient();
    }

}
