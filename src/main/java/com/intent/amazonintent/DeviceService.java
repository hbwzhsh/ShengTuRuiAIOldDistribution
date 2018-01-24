package com.intent.amazonintent;

import com.SpringUtil;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.IntendType;
import com.bean.User;
import com.bean.site.UserSite;
import com.init.Constants;
import com.init.ConstantsMethod;
import com.intent.amazonintent.refacting.DeviceTypeFactory;
import com.mapper.UserMapper;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LogManager.getLogger(DeviceService.class);


    public void createSocketSession(final String userId) {
        SoketClient client = SocketFactory.socketConnections.get(userId);
        client.connectService(userId);
    }


    public void sendCmdToServer(final List<Device> tempDevicelist, final String cmd, final String userId) {

        SoketClient client = SocketFactory.socketConnections.get(userId);
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = new ArrayList<String>();
            List<String> tempDeviceHostList = new ArrayList<String>();

            for (Device tempDevice : tempDevicelist) {
                logger.debug("--->Cmd:" + "CMD:" + tempDevice.getDeviceMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + "00");

                tempDeviceMacList.add("CMD:" + tempDevice.getDeviceMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + "00");
                tempDeviceHostList.add(tempDevice.getHostMac());

                //ConstantsMethod.reSetDevicedata(tempDevice, item.getPersentage() + "");
            }

            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServer(final Collection<Device> tempDevicelist, final String cmd, final IntendParams item) {

        SoketClient client = SocketFactory.socketConnections.get(item.getUserId());
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = new ArrayList<String>();
            List<String> tempDeviceHostList = new ArrayList<String>();

            for (Device tempDevice : tempDevicelist) {
                logger.debug("--->Cmd:" + "CMD:" + tempDevice.getDeviceMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + item.getDeviceState());

                tempDeviceMacList.add("CMD:" + tempDevice.getDeviceMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + item.getDeviceState());
                tempDeviceHostList.add(tempDevice.getHostMac());

                ConstantsMethod.reSetDevicedata(tempDevice, item.getPersentage() + "");
            }

            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServerForOpenAlittle(final Collection<Device> tempDevicelist, final String cmd, final boolean flag, final IntendParams item) {
        SoketClient client = SocketFactory.socketConnections.get(item.getUserId());
        Runnable runnable = () -> {
            List<String> tempDeviceMacList = new ArrayList<String>();
            List<String> tempDeviceHostList = new ArrayList<String>();

            for (Device tempDevice : tempDevicelist) {
                int moveToProcessBar = 0;

                if (StringUtils.isBlank(tempDevice.getProgressBar())) {
                    tempDevice.setProgressBar("0");
                }

                if (StringUtils.isNumeric(tempDevice.getProgressBar())) {
                    logger.debug("currentProcessBar:" + tempDevice.getProgressBar());
                    int currentProcessBar = Integer.parseInt(tempDevice.getProgressBar());
                    if (flag) {
                        moveToProcessBar = currentProcessBar + Constants.MOVECURTAINSPERCENTS;
                        moveToProcessBar = (moveToProcessBar >= 100) ? 100 : moveToProcessBar;
                    } else {
                        moveToProcessBar = currentProcessBar - Constants.MOVECURTAINSPERCENTS;
                        moveToProcessBar = (moveToProcessBar <= 0) ? 0 : moveToProcessBar;
                    }
                }

                ConstantsMethod.reSetDevicedata(tempDevice, moveToProcessBar + "");

                logger.debug("moveToProcessBar:" + moveToProcessBar);
                tempDeviceMacList.add("CMD:" + tempDevice.getDeviceMac() + "-" + tempDevice.getEquipmentEp() + "-" + cmd + "-" + ConstantsMethod.getProcessBarCmd(moveToProcessBar + ""));
                tempDeviceHostList.add(tempDevice.getHostMac());
            }

            client.connectServiceAndExeCommand(tempDeviceMacList, tempDeviceHostList);
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * amazonId suddenly changed or add a new device or delete a device or new
     * User
     */
    public boolean refreshCacheData(final String amazonId) {

        logger.debug("start to refresh data...amazonId:" + amazonId);

        UserSite userTemp = new UserSite();
        userTemp.setEmail(amazonId);

        final UserSite user = SpringUtil.getUserMapper().getObjectByCondition(userTemp);
        logger.debug("find the user from the database:" + user.getUserId());
        {
            Runnable runnable = () -> {
                List<Device> listDevice = SpringUtil.getUserMapper().getDeviceList(user.getUserId());
                for (Device item : listDevice) {
                    item.setAmazonId(amazonId);
                    for (Device device : Constants.deviceList) {
                        if (device.getDeviceMac().equals(item.getDeviceMac()) && device.getEquipmentEp().equals(item.getEquipmentEp())) {
                            device.setAmazonId(amazonId);
                            device.setRoomName(item.getRoomName());
                            device.setDevid(item.getDevid());
                            device.setName(item.getName());
                            logger.debug("update old data.." + item.getRoomName());
                        }
                    }
                }
                logger.debug("before adding:" + Constants.deviceList.size());
                Constants.deviceList.addAll(listDevice);
                //ConstantsMethod.updateDeviceLists(Constants.deviceList);
                logger.debug("after adding:" + Constants.deviceList.size());
                createSocketSession(user.getUserId());
            };

            Thread thread = new Thread(runnable);
            thread.start();
            logger.debug("start to connect the socket...");
            return true;
        }
    }

    public List<Device> getSameTypeDevices(String intentName, String amazonId) {
        return Constants.deviceList.stream().filter(device -> (device.getAmazonId().equals(amazonId) && linkIntentNameAndDevice(intentName, device.getDevid()))).collect(Collectors.toList());
    }

    private boolean linkIntentNameAndDevice(String intendName, String deviceId) {

        IntendType deviceType = DeviceTypeFactory.getDeviceByIntendName(intendName);

        if (deviceType != null) {
            if (deviceType.getDeviceIds().contains(deviceId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * deviceName is empty add all of them or getAmazonId getDevid where
     * deviceName
     *
     * @param intentName
     * @param where
     * @param deviceName
     * @param amazonId
     * @return
     */
    public List<Device> getAllLightsFromDevices(String intentName, String where, String deviceName, String amazonId) {

        List<Device> lights = new ArrayList<Device>();

        for (Device device : getAllCurtainsFromDevices(intentName, where, amazonId)) {

            if (StringUtils.isBlank(deviceName)) {
                lights.add(device);
            } else if (StringUtils.isNotBlank(device.getName()) & delelteBlankPartFromString(deviceName).equalsIgnoreCase(delelteBlankPartFromString(device.getName()))) {
                lights.add(device);
            }

        }
        return lights;
    }

    public List<Device> getAllCurtainsFromDevices(String intentName, String where, String amazonId) {
        List<Device> curtains = new ArrayList<Device>();
        for (Device device : Constants.deviceList) {
            if (device.getRoomName() != null & device.getAmazonId().equalsIgnoreCase(amazonId) && linkIntentNameAndDevice(intentName, device.getDevid()) && delelteBlankPartFromString(where).indexOf(delelteBlankPartFromString(device.getRoomName())) != -1) {
                curtains.add(device);
            }
        }
        return curtains;
    }

    private String delelteBlankPartFromString(String str) {
        if (StringUtils.isBlank(str))
            return StringUtils.EMPTY;
        return str.replaceAll("\\s*", "").toLowerCase();
    }

    public static void main(String[] args) {
        logger.debug(new DeviceService().delelteBlankPartFromString("zebra 6"));

    }

    public List<Device> filterDataByIntentName(IntendParams item) {
        // TODO Auto-generated method stub

        List<Device> filterDevice = new ArrayList<Device>();

        if (Constants.wholeHouse.equalsIgnoreCase(item.getWhere())) {
            filterDevice = getSameTypeDevices(item.getIntentName(), item.getAccessToken());
        } else {
            filterDevice = getAllLightsFromDevices(item.getIntentName(), item.getWhere(), item.getDevicename(), item.getAccessToken());
        }
        return filterDevice;
    }

}
