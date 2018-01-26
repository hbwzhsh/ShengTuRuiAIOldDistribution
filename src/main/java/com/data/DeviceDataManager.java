package com.data;

import com.SpringUtil;
import com.bean.Device;
import com.bean.SpeakerUsers;
import com.bean.site.UserOauth2;
import com.service.DeviceService;
import com.socket.SocketFactory;
import com.socket.SoketClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceDataManager {

    private static int delayTime = 5000;
    private static int period = 20000000;

    public static void updateDataSchedule() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    List<SpeakerUsers> usersTempList = SpringUtil.getUserMapper().getUsersTemp();
                    for (SpeakerUsers users : usersTempList) {
                        int countNumber = SpringUtil.getUserMapper().getDeviceWithNoNameList(users.getUserId());
                        if (countNumber > 0) {

                            SoketClient tempClient = SocketFactory.socketConnections.get(users.getUserId());
                            if (tempClient != null) {
                                boolean isConnected = tempClient.getSendsession().isConnected();
                                if (!isConnected) {
                                    SocketFactory.socketConnections.remove(users.getUserId());
                                    tempClient = null;
                                }
                            }

                            if (tempClient == null) {
                                SoketClient client = new SoketClient();
                                SocketFactory.socketConnections.put(users.getUserId(), client);
                                client.connectService(users.getUserId());
                            } else {
                                tempClient.getDevicesFromService(users.getUserId());
                            }

                            //get data from database then put data to redis cache
                            List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());
                            RedisDAO.saveObject( users.getUserId() , deviceList );
                        }

                        Object obj = RedisDAO.getObject( users.getUserId());
                        if(obj == null){
                            List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());
                            RedisDAO.saveObject( users.getUserId() , deviceList );
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, delayTime, period);
    }

    public static void main(String[] args) {



    }
}
