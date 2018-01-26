package com.socket;

import com.SpringUtil;
import com.bean.UserHostRelation;
import com.utility.AesUtil;
import com.utility.CmdUtil;
import com.utility.Constants;
import com.utility.ToHexUtil;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoketClient {
    private AesUtil mAesUtil = new AesUtil();

    private IoSession sendsession;

    public static void main(String[] args) {
        List<String> commandList  = Arrays.asList("CMD:7bff6314004b1200-00-00-0000");
        List<String> hostMacs = Arrays.asList("9C65F9209CE8");
        SoketClient client = new SoketClient();
        SocketFactory.socketConnections.put("10132", client);
        client.connectService("10132");
        client.connectServiceAndExeCommand(commandList,hostMacs);



        List<String> commandList1  = Arrays.asList("CMD:7bff6314004b1200-00-01-0000");

        SoketClient tempClient = SocketFactory.socketConnections.get("10132");
        tempClient.connectServiceAndExeCommand(commandList1,hostMacs);
    }

    public void connectServiceAndExeCommand(List<String> commandList, List<String> hostMacs) {
       /* NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(500000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));// �������������ַ������б��
        connector.setHandler(new ServiceClientHandler());*/
        try {

          /*  ConnectFuture future = connector.connect(new InetSocketAddress(Constants.serverIpConnectSocket, Integer.parseInt(Constants.socketPort)));
            future.awaitUninterruptibly();
            sendsession = future.getSession();*/
            for (int i = 0; i < commandList.size(); i++) {

                byte[] msg4 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, commandList.get(i), hostMacs.get(i)));

                System.out.println("commandList.get(i):" + commandList.get(i));
                System.out.println("hostMacs.size():" + hostMacs.size());
                System.out.println("------------------------>sending just a second.........");
                sendsession.write(IoBuffer.wrap(msg4));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //sendsession.getCloseFuture().awaitUninterruptibly();
        //connector.dispose();
    }


    public void getDevicesFromService(String userId) {
       /* NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(500000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));// �������������ַ������б��
        connector.setHandler(new ServiceClientHandler());*/
            try {
                System.out.println("-----------> session.getId():" + sendsession.getId());

                UserHostRelation userHostRelation = new UserHostRelation();
                userHostRelation.setUserId(userId);
                List<UserHostRelation> listStr = SpringUtil.getUserMapper().getUserHostRelation(userHostRelation);

                for (UserHostRelation host : listStr) {

                    byte[] msg = ToHexUtil.hexStringToByte(CmdUtil.connectService(mAesUtil, host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg));

                    byte[] msg1 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getDevice(), host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg1));

                    byte[] msg5 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getEndPoint(), host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg5));

                }
                System.out.println("refresh data...");
            } catch (RuntimeIoException e) {
                System.out.println("123123");
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
    }

    public void connectService(String userId) {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(500000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));// �������������ַ������б��
        connector.setHandler(new ServiceClientHandler());

            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(Constants.serverIpConnectSocket, Integer.parseInt(Constants.socketPort)));
                future.awaitUninterruptibly();
                sendsession = future.getSession();
                SocketFactory.socketConnections.put(userId, this);
                SocketFactory.AesConnections.put( sendsession.getId()+"",this.mAesUtil );
                System.out.println("-----------> session.getId():" + sendsession.getId());

                UserHostRelation userHostRelation = new UserHostRelation();
                userHostRelation.setUserId(userId);
                //List<UserHostRelation> listStr = SpringUtil.getUserMapper().getUserHostRelation(userHostRelation);

                List<UserHostRelation> listStr = new ArrayList<>();
                UserHostRelation userHostRelation1 = new UserHostRelation();
                userHostRelation1.setMac("9C65F9209CE8");

                for (UserHostRelation host : listStr) {

                    byte[] msg = ToHexUtil.hexStringToByte(CmdUtil.connectService(mAesUtil, host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg));

                    byte[] msg1 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getDevice(), host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg1));

                    byte[] msg5 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getEndPoint(), host.getMac()));
                    sendsession.write(IoBuffer.wrap(msg5));

                }
                System.out.println("refresh data...");
            } catch (RuntimeIoException e) {
                System.out.println("123123");
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        // wait until the summation is done
/*        if (sendsession != null)
            sendsession.getCloseFuture().awaitUninterruptibly();*/
        //connector.dispose();
    }



    public IoSession getSendsession() {
        return sendsession;
    }

    public void setSendsession(IoSession sendsession) {
        this.sendsession = sendsession;
    }

    public AesUtil getmAesUtil() {
        return mAesUtil;
    }

    public void setmAesUtil(AesUtil mAesUtil) {
        this.mAesUtil = mAesUtil;
    }

    public static String getDevice() {
        return "GET\\DEVICE:";
    }

    public static String getEndPoint() {
        return "GET\\ENDPOINT:";
    }
}

// MINA����
class ByteArrayEncoder extends ProtocolEncoderAdapter { // Encoder����

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
		out.write(message);
		out.flush();
	}
}

// MINA����
class ByteArrayDecoder extends ProtocolDecoderAdapter {
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		int limit = in.limit();
		byte[] bytes = new byte[limit];
		in.get(bytes);
		out.write(bytes);
	}
}

// MINA����
class ByteArrayCodecFactory implements ProtocolCodecFactory {
	private ByteArrayDecoder decoder;
	private ByteArrayEncoder encoder;

	public ByteArrayCodecFactory() {
		encoder = new ByteArrayEncoder();
		decoder = new ByteArrayDecoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}
}
