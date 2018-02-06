package com.service;

import com.SpringUtil;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.IntendType;
import com.data.DeviceDataManager;
import com.utility.Constants;
import com.data.DeviceTypeFactory;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import com.utility.ConstantsMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * provide service for DeviceSpeechlet
 */
public class DeviceService {
    private StringRedisTemplate stringRedisTemplate =(StringRedisTemplate) SpringUtil.getBean("stringRedisTemplate");
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    public void sendCmdToServerForOpenAlittle(final List<Device> tempDevicelist, final String cmd,String percent, final String userId) {
        SoketClient client = SocketFactory.socketConnections.get(userId);
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + ConstantsMethod.getProcessBarCmd(percent)).collect(Collectors.toList());
            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


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
            List<String> tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + ConstantsMethod.getProcessBarCmd(item.getDeviceFuturePosition())).collect(Collectors.toList());
            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServerForOpenAlittle(final Collection<Device> tempDevicelist, final String cmd, final boolean flag, final IntendParams item) {
        SoketClient client = SocketFactory.socketConnections.get(item.getUserId());
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = new ArrayList<>();

            if(StringUtils.isBlank(item.getPersentage())){
                tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + ConstantsMethod.getProcessBarCmd(getMoveToProcessBar(flag, tempDevice)+"")).collect(Collectors.toList());
            }else{
                tempDeviceMacList = tempDevicelist.stream().map(tempDevice -> "CMD:" + tempDevice.getEquipmentMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + ConstantsMethod.getProcessBarCmd(item.getPersentage())).collect(Collectors.toList());
            }

            List<String> tempDeviceHostList = tempDevicelist.stream().map(tempDevice->tempDevice.getHostMac()).collect(Collectors.toList());
            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private int getMoveToProcessBar(boolean flag, Device tempDevice) {
        int moveToProcessBar = 0;
        String currentProccessBar =stringRedisTemplate.opsForValue().get(ConstantsMethod.devicePKey(tempDevice.getEquipmentMac(),tempDevice.getEquipmentEp()));

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
        System.out.println(deviceType.getDeviceIds().size());

        return dataList.stream().filter(device -> {

            if (deviceType != null) {
                if (deviceType.getDeviceIds().contains(device.getDevid())) {
                    System.out.println("device.getDevid():"+device.getDevid());
                    return true;
                }
            }

            return false;
        }).collect(Collectors.toList());
    }

    public List<Device> getDeviceByCondition(IntendParams item, List<Device> dataList) {

        List<Device> findingDevices = dataList;
        findingDevices = getSameTypeDevices(item.getIntentName(), dataList);
        logger.debug("find getSameTypeDevices data size():"+dataList.size());
        if(StringUtils.isNotBlank(item.getWhere())){
            findingDevices = findingDevices.stream().filter(device->(deleteExtraBlanks(item.getWhere()).indexOf(deleteExtraBlanks(device.getRoomName())) != -1)).collect(Collectors.toList());
        }

        if(StringUtils.isNotBlank(item.getDevicename())){
            findingDevices = findingDevices.stream().filter(device->(deleteExtraBlanks(item.getDevicename()).equalsIgnoreCase(deleteExtraBlanks(device.getName())))).collect(Collectors.toList());
        }
        logger.debug("find getSameTypeDevices data size():"+findingDevices.size());
        return findingDevices;
    }

    private String deleteExtraBlanks(String str) {
        if (StringUtils.isBlank(str))
            return StringUtils.EMPTY;
        return str.replaceAll("\\s*", "").toLowerCase();
    }

    public List<Device> filterDataByIntentName(IntendParams item) {
        // TODO Auto-generated method stub
        List<Device> dataList = DeviceDataManager.getDeviceList(item.getUserId());

        logger.debug("filterDataByIntentName():"+ dataList.size());
        List<Device> filterDevice = new ArrayList<Device>();
        if (Constants.wholeHouse.equalsIgnoreCase(item.getWhere())) {
            filterDevice = getSameTypeDevices(item.getIntentName(), dataList);
        } else {
            filterDevice = getDeviceByCondition(item, dataList);
        }
        return filterDevice;
    }

}
