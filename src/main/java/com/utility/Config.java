package com.utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${socketPort}")
    public  String socketPort;

    @Value("${serverIpConnectSocket}")
    public  String serverIpConnectSocket;

    @Value("${clientId}")
    public  String clientId;

    @Value("${secret}")
    public  String secret;

    public String getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(String socketPort) {
        this.socketPort = socketPort;
    }

    public String getServerIpConnectSocket() {
        return serverIpConnectSocket;
    }

    public void setServerIpConnectSocket(String serverIpConnectSocket) {
        this.serverIpConnectSocket = serverIpConnectSocket;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
