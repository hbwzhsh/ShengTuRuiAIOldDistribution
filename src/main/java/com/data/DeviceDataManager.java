package com.data;

import com.SpringUtil;
import com.bean.Device;
import com.bean.SpeakerUsers;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import com.utility.ConstantsMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class DeviceDataManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int delayTime = 10000;
    private static int period = 100000;
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

    public void updateDataSchedule() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    countTime++;
                    //System.out.println("countTime:" + countTime);
                    logger.debug("countTime:" + countTime);
                    List<SpeakerUsers> usersTempList = SpringUtil.getUserMapper().getUsersTemp().stream().filter(item-> StringUtils.isNotBlank(item.getUserId())).collect(Collectors.toList());
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
                            System.out.println("new connection:" + countTime);
                            logger.debug("new connection:" + countTime);
                            SoketClient client = new SoketClient();
                            client.connectService(users.getUserId());
                        } else {
                            //System.out.println("old connection:" + countTime);
                            tempClient.getDevicesFromService(users.getUserId());
                        }
                        //get data from database then put data to redis cache
                        List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(users.getUserId());

                        if (deviceList == null || deviceList.size() == 0) {
                            continue;
                        }

                       // List<Device> deviceResult = getDevicesByType(deviceList);


                        for (Device item : deviceList) {
                            String name = stringRedisTemplate.opsForValue().get(ConstantsMethod.deviceNameKey(item.getEquipmentMac(), item.getEquipmentEp()));
                            if (StringUtils.isBlank(name)) continue;

                            String deviceName = name.split(":")[0];
                            String deviceVid = name.split(":")[1];
                            if (!deviceName.equalsIgnoreCase(item.getName()) || !deviceVid.equalsIgnoreCase(item.getDevid())) {
                                item.setName(deviceName);
                                item.setDevid(deviceVid);
                                logger.debug("update database:" + deviceName);
                                SpringUtil.getUserMapper().updateHouseRelation(item);
                            }
                        }
                        //logger.debug("devices from database:" + deviceList.size());
                        //redisTemplate.opsForValue().set(users.getUserId(), deviceResult);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, delayTime, period);
    }

    private static List<Device> getDevicesByType(List<Device> deviceList) {
        return deviceList.stream().filter(item -> DeviceTypeFactory.allowDeviceList.contains(item.getDevid())).collect(Collectors.toList());
    }


    public static List<Device> getDeviceList(String userId) {
        List<Device> deviceData = SpringUtil.getUserMapper().getDeviceList(userId);
        for (Device device : deviceData) {
            String proccessBar = stringRedisTemplate.opsForValue().get(ConstantsMethod.devicePKey(device.getEquipmentMac(), device.getEquipmentEp()));
            device.setProgressBar(proccessBar);
        }
        return getDevicesByType(deviceData);
    }
}
