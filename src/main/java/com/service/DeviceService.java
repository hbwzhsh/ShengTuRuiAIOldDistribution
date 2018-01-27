package com.service;

import com.SpringUtil;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.IntendType;
import com.data.DeviceDataManager;
import com.utility.Constants;
import com.intent.amazonintent.refacting.DeviceTypeFactory;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * provide service for DeviceSpeechlet
 */
@Component
public class DeviceService {

    private StringRedisTemplate stringRedisTemplate =(StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");

    private static final Logger logger = LogManager.getLogger(DeviceService.class);

    public void sendCmdToServer(final List<Device> tempDevicelist, final String cmd, final String userId) {
        SoketClient client = SocketFactory.socketConnections.get(userId);
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + Constants.defaultDeviceState).collect(Collectors.toList());
            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServer(final Collection<Device> tempDevicelist, final String cmd, final IntendParams item) {
        SoketClient client = SocketFactory.socketConnections.get(item.getUserId());
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + item.getDeviceState()).collect(Collectors.toList());
            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServerForOpenAlittle(final Collection<Device> tempDevicelist, final String cmd, final boolean flag, final IntendParams item) {
        SoketClient client = SocketFactory.socketConnections.get(item.getUserId());
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + getMoveToProcessBar(flag, tempDevice)).collect(Collectors.toList());
            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private int getMoveToProcessBar(boolean flag, Device tempDevice) {
        int moveToProcessBar = 0;
        String currentProccessBar =stringRedisTemplate.opsForValue().get(tempDevice.getEquipmentMac()+":"+tempDevice.getEquipmentEp());

        if (StringUtils.isBlank(currentProccessBar)) {
            return moveToProcessBar;
        }
        if (StringUtils.isNumeric(currentProccessBar)) {
            int currentProcessBar = Integer.parseInt(currentProccessBar);
            if (flag) {
                moveToProcessBar = currentProcessBar + Constants.MOVECURTAINSPERCENTS;
                moveToProcessBar = (moveToProcessBar >= 100) ? 100 : moveToProcessBar;
            } else {
                moveToProcessBar = currentProcessBar - Constants.MOVECURTAINSPERCENTS;
                moveToProcessBar = (moveToProcessBar <= 0) ? 0 : moveToProcessBar;
            }
        }
        return moveToProcessBar;
    }

    private List<Device> getSameTypeDevices(String intendName, List<Device> dataList) {
        IntendType deviceType = DeviceTypeFactory.getDeviceByIntendName(intendName);
        return dataList.stream().filter(device -> {
            if (deviceType != null) {
                if (deviceType.getDeviceIds().contains(device.getDevid())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());

    }

    public List<Device> getDeviceByCondition(IntendParams item, List<Device> dataList) {

        dataList = getSameTypeDevices(item.getIntentName(), dataList);
        System.out.println("find getSameTypeDevices data size():"+dataList.size());

        List<Device> lights = new ArrayList<Device>();
        for (Device device : dataList) {
            if (StringUtils.isBlank(item.getDevicename())) {
                lights.add(device);
            }else if (device.getRoomName() != null && deleteExtraBlanks(item.getWhere()).indexOf(deleteExtraBlanks(device.getRoomName())) != -1) {
                lights.add(device);
            }
            else if (StringUtils.isNotBlank(device.getName()) & deleteExtraBlanks(item.getDevicename()).equalsIgnoreCase(deleteExtraBlanks(device.getName()))) {
                lights.add(device);
            }
        }

        System.out.println("find getDeviceByCondition data size():"+lights.size());
        return lights;
    }

    private String deleteExtraBlanks(String str) {
        if (StringUtils.isBlank(str))
            return StringUtils.EMPTY;
        return str.replaceAll("\\s*", "").toLowerCase();
    }

    public List<Device> filterDataByIntentName(IntendParams item) {
        // TODO Auto-generated method stub
        List<Device> dataList = DeviceDataManager.getDeviceList(item.getUserId());

        List<Device> filterDevice = new ArrayList<Device>();
        if (Constants.wholeHouse.equalsIgnoreCase(item.getWhere())) {
            filterDevice = getSameTypeDevices(item.getIntentName(), dataList);
        } else {
            filterDevice = getDeviceByCondition(item, dataList);
        }
        return filterDevice;
    }

}
