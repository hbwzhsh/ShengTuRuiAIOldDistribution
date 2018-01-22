package com.init;

import com.model.IntendParams;
import com.netty.NettySendService;
import com.netty.model.Device;
import com.redis.RedisDAO;
import com.utility.ToHexUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Servlet implementation class InitializeData
 */
@Component
public class ConstantsMethod {



    private static Logger logger = LoggerFactory.getLogger(ConstantsMethod.class);

    public static void addDefualRoom(String accessToken, String roomName) {

        Constants.defualtRooms.remove(accessToken);
        Constants.defualtRooms.put(accessToken, roomName);

        RedisDAO.deleteObject(Constants.dafualtRoomKey);
        RedisDAO.saveObject(Constants.dafualtRoomKey, Constants.defualtRooms);
    }

    @SuppressWarnings("unchecked")
    public static String getDefualRoom(String key) {

        if (Constants.defualtRooms.size() == 0) {
            Constants.defualtRooms = RedisDAO.getHashMap(Constants.dafualtRoomKey);
        }

        String defualRoom = Constants.defualtRooms.get(key);
        return defualRoom;
    }

    public static void updateDeviceLists(Set<Device> lists) {
        RedisDAO.deleteObject(Constants.deviceKey);
        RedisDAO.saveObject(Constants.deviceKey, lists);
    }


    public static String getProcessBarCmd(String persentage) {
        if (StringUtils.isBlank(persentage)) {
            return "0";
        }
        return ToHexUtil.pad(ToHexUtil.toHex(Integer.parseInt(persentage)), 4, true);
    }


    public static List<Device> getDevicelistByUserId(IntendParams params) {
        logger.debug("getDevicelistByUserId-------->:" + params.getUserId());
        Object obj = Constants.logicDeviceList.get(params.getUserId());
        logger.debug("getDevicelistByUserId-------->obj:" + obj);
        if (obj != null) {
            List<Device> deviceList = (ArrayList) obj;
            return deviceList;
        } else {
//			new HttpServiceClient().updateDataFromServer(params);
            new NettySendService().newConnection(Integer.parseInt(params.getUserId()));
            new NettySendService().clientgetallDescription(Integer.parseInt(params.getUserId()));
            logger.debug("logicDeviceList-------->:" + Constants.logicDeviceList.size());
            List<Device> tempList = (ArrayList) Constants.logicDeviceList.get(params.getUserId());
            if(tempList == null){
                tempList = new ArrayList<>();
            }
            return tempList;
        }
    }
}
