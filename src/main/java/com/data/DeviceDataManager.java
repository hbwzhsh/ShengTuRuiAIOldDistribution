package com.data;

import com.SpringUtil;
import com.bean.Device;
import com.bean.SpeakerUsers;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import com.utility.ConstantsMethod;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceDataManager {

    private static int delayTime = 1000;
    private static int period = 10000;
    private static int countTime = 0;

    private static RedisTemplate redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");

    private static StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    /*public static void cleanSocketClient(String userId) {
        SoketClient tempClient = SocketFactory.socketConnections.get(userId);
        if (tempClient != null) {
            SocketFactory.socketConnections.remove(userId);
        } else {
        }
    }*/

    public static void updateDataSchedule() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {

                    countTime++;
                    System.out.println("countTime:" + countTime);
                    List<SpeakerUsers> usersTempList = SpringUtil.getUserMapper().getUsersTemp();
                    for (SpeakerUsers users : usersTempList) {
                     /*   int countNumber = SpringUtil.getUserMapper().getDeviceWithNoNameList(users.getUserId());
                        if (countNumber > 0) {*/

                        SoketClient tempClient = SocketFactory.socketConnections.get(users.getUserId());
                        if (tempClient != null) {
                            boolean isConnected = tempClient.getSendsession().isConnected();
                            if (!isConnected) {
                                SocketFactory.socketConnections.remove(users.getUserId());
                                tempClient = null;
                            }
                        }

                        if (tempClient == null) {
                            System.out.println("new connectiono:" + countTime);
                            SoketClient client = new SoketClient();
                            client.connectService(users.getUserId());
                        } else {
                            System.out.println("old connection:"+countTime);
                            tempClient.getDevicesFromService(users.getUserId());
                        }
                        //get data from database then put data to redis cache
                        List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());
                        for (Device item : deviceList) {
                            String name = stringRedisTemplate.opsForValue().get(ConstantsMethod.deviceNameKey(item.getEquipmentMac(), item.getEquipmentEp()));
                            String deviceName = name.split(":")[0];
                            String deviceVid = name.split(":")[1];
                            if (!item.getName().equalsIgnoreCase(name) || !deviceVid.equalsIgnoreCase(item.getDevid())) {
                                item.setName(deviceName);
                                item.setDevid(deviceVid);
                                SpringUtil.getUserMapper().updateHouseRelation(item);
                            }
                        }

                        redisTemplate.opsForValue().set(users.getUserId(), deviceList);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, delayTime, period);
    }


    public static List<Device> getDeviceList(String userId) {
        List<Device> deviceList = (ArrayList<Device>) redisTemplate.opsForValue().get(userId);

        System.out.println("getDeviceList():" + deviceList.size());

        if (deviceList == null) {
            return SpringUtil.getUserMapper().getDeviceList(userId);
        }

        for (Device device : deviceList) {
            String proccessBar = stringRedisTemplate.opsForValue().get(ConstantsMethod.devicePKey(device.getEquipmentMac(), device.getEquipmentEp()));
            device.setProgressBar(proccessBar);

          /*  String name = stringRedisTemplate.opsForValue().get(ConstantsMethod.deviceNameKey( device.getEquipmentMac(), device.getEquipmentEp()));
            device.setName(name);*/
        }
        return deviceList;
    }
}
