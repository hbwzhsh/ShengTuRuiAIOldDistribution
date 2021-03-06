package com.socket.newTest;

import com.SpringUtil;
import com.bean.UserHostRelation;
import com.service.DeviceService;
import com.socket.ServiceClientHandler;
import com.utility.AesUtil;
import com.utility.CmdUtil;
import com.utility.Constants;
import com.utility.ToHexUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.List;

public class SoketClient {

	//private static DbDAO dbdao = new DbDAO();
	private AesUtil mAesUtil = new AesUtil();
	
	private static final Logger logger = LogManager.getLogger(DeviceService.class);

	public void connectServiceAndExeCommand(List<String> commandList, List<String> hostMacs) {
		logger.error("commandList.size():"+commandList.size());
		logger.error("hostMacs.size():"+hostMacs.size());
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(50000);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));// �������������ַ������б��
		connector.setHandler(new ServiceClientHandler());
		IoSession sendsession = null;
		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(Constants.serverIpConnectSocket, Integer.parseInt(Constants.socketPort)));
				future.awaitUninterruptibly();
				sendsession = future.getSession();

				for (String hostMac : hostMacs) {
					Thread.sleep(2);
					byte[] msg = ToHexUtil.hexStringToByte(CmdUtil.connectService(mAesUtil, hostMac));
					sendsession.write(IoBuffer.wrap(msg));
				}

				for (int i = 0; i < commandList.size(); i++) {
					logger.error("commandList.get(i):"+commandList.get(i));
					String str = CmdUtil.getToService(mAesUtil, commandList.get(i), hostMacs.get(i));
					byte[] msg4 = ToHexUtil.hexStringToByte(str);
					sendsession.write(IoBuffer.wrap(msg4));

				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		sendsession.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

	public void connectService(String userId) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(500000);
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));// �������������ַ������б��
		connector.setHandler(new ServiceClientHandler());
		IoSession session = null;
		
		for (;;) {
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(Constants.serverIpConnectSocket, Integer.parseInt(Constants.socketPort)));
				future.awaitUninterruptibly();
				session = future.getSession();

				UserHostRelation userHostRelation = new UserHostRelation();
				userHostRelation.setUserId(userId);
				List<UserHostRelation> listStr = SpringUtil.getUserMapper().getUserHostRelation(userHostRelation);

				for (UserHostRelation host : listStr) {

					byte[] msg = ToHexUtil.hexStringToByte(CmdUtil.connectService(mAesUtil, host.getMac()));
					session.write(IoBuffer.wrap(msg));

					byte[] msg1 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getDevice(), host.getMac()));
					session.write(IoBuffer.wrap(msg1));

					byte[] msg5 = ToHexUtil.hexStringToByte(CmdUtil.getToService(mAesUtil, getEndPoint(), host.getMac()));
					session.write(IoBuffer.wrap(msg5));

				}
				System.out.println("refresh data...");
				break;
			} catch (RuntimeIoException e) {
				System.out.println("123123");
				e.printStackTrace();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
		
		// wait until the summation is done
		if(session != null)
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
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
