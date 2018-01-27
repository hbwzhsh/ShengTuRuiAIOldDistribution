package com.data;

import com.SpringUtil;
import com.bean.Device;
import com.bean.SpeakerUsers;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceDataManager {

    private static int delayTime = 1000;
    private static int period = 20000;

    private static RedisTemplate redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");

    private static StringRedisTemplate stringRedisTemplate =(StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

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
                                client.connectService(users.getUserId());
                            } else {
                                tempClient.getDevicesFromService(users.getUserId());
                            }
                            //get data from database then put data to redis cache
                            List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());
                            redisTemplate.opsForValue().set(users.getUserId(),deviceList);
                        }

                        Object obj =  redisTemplate.opsForValue().get(users.getUserId());
                        if (obj == null) {
                            List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());
                            redisTemplate.opsForValue().set(users.getUserId(),deviceList);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }, delayTime, period);
    }


    public static List<Device> getDeviceList(String userId){
        List<Device> deviceList = (ArrayList<Device>)redisTemplate.opsForValue().get("userId");
        for(Device device : deviceList){
            String proccessBar =stringRedisTemplate.opsForValue().get( device.getEquipmentMac()+":"+device.getEquipmentEp());
            device.setProgressBar(proccessBar);
        }
        return deviceList;
    }
}
