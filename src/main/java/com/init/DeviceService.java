package com.init;

import com.intends.util.DeviceTypeFactory;
import com.model.IntendParams;
import com.model.IntendType;
import com.netty.NettySendService;
import com.netty.model.Device;
import com.netty.model.IdValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * provide service for DeviceSpeechlet
 *
 * @author louie
 */
public class DeviceService {

    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);
    private NettySendService nettySendService = new NettySendService();
    private HttpServiceClient httpServiceClient = new HttpServiceClient();

    public void sendCmdToServer(final Collection<Device> tempDevicelist, final String cmd, final IntendParams item) {
        Runnable runnable = () -> {
            for (Device tempDevice : tempDevicelist) {
                nettySendService.newCommand(item.getUserId(), tempDevice.getDeviceMac(), item.getOp(), Integer.parseInt(cmd));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendCmdToServerForOpenAlittle(final Collection<Device> tempDevicelist, final String cmd, final boolean flag, final IntendParams item) {
        Runnable runnable = () -> {

            for (Device tempDevice : tempDevicelist) {
                int moveToProcessBar = 0;
                int currentProcessBar = tempDevice.getAttrlist().stream().filter(temp -> temp.getId() == 4).mapToInt(IdValue::getValue).findFirst().orElse(0);
                logger.debug("currentProcessBar:" + currentProcessBar);
                if (flag) {
                    moveToProcessBar = currentProcessBar + Constants.MOVECURTAINSPERCENTS;
                    moveToProcessBar = (moveToProcessBar >= 100) ? 100 : moveToProcessBar;
                } else {
                    moveToProcessBar = currentProcessBar - Constants.MOVECURTAINSPERCENTS;
                    moveToProcessBar = (moveToProcessBar <= 0) ? 0 : moveToProcessBar;
                }
                System.out.println("moveToProcessBar:" + moveToProcessBar);
                nettySendService.newCommand(item.getUserId(), tempDevice.getDeviceMac(), moveToProcessBar, Integer.parseInt(item.getDeviceCMD()));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * amazonId suddenly changed or add a new device or delete a device or new
     * User
     */
    public boolean refreshCacheData(final String userId, String token) {
        nettySendService.clientgetallDescription(Integer.parseInt(userId));
        return true;
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
     * @return
     */
    public List<Device> getAllLightsFromDevices(IntendParams params) {
        return ConstantsMethod.getDevicelistByUserId(params).stream()
                .filter(device -> linkIntentNameAndDevice(params.getIntentName(), device.getDevid() + ""))
                .filter(device -> StringUtils.isBlank(params.getWhere()) || delelteBlankPartFromString(params.getWhere()).indexOf(delelteBlankPartFromString(device.getRoomName())) != -1)
                .filter(device -> StringUtils.isBlank(params.getDeviceName()) || delelteBlankPartFromString(params.getDeviceName()).equalsIgnoreCase(delelteBlankPartFromString(device.getCustomName())))
                .collect(Collectors.toList());
    }

    private String delelteBlankPartFromString(String str) {
        if (StringUtils.isBlank(str))
            return StringUtils.EMPTY;
        return str.replaceAll("\\s*", "").toLowerCase();
    }

    public List<Device> filterDataByIntentName(IntendParams item) {
        // TODO Auto-generated method stub
        List<Device> filterDevice = new ArrayList<Device>();
        if (Constants.wholeHouse.equalsIgnoreCase(item.getWhere())) {
            filterDevice = getSameTypeDevices(item);
        } else {
            filterDevice = getAllLightsFromDevices(item);
        }
        return filterDevice;
    }

    public List<Device> getSameTypeDevices(IntendParams item) {
        return ConstantsMethod.getDevicelistByUserId(item).stream().filter(device -> (linkIntentNameAndDevice(item.getIntentName(), device.getDevid() + ""))).collect(Collectors.toList());
    }

}
