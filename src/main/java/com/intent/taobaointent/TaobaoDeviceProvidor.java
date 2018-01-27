package com.intent.taobaointent;

import com.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.bean.Device;
import com.bean.google.newpojo.command.request.Devices;
import com.bean.google.newpojo.command.request.Execution;
import com.bean.site.UserOauth2;
import com.bean.taobao.*;
import com.data.DeviceDataManager;
import com.data.RedisDAO;
import com.service.DeviceService;
import com.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class TaobaoDeviceProvidor {

    @Autowired
    private static RedisTemplate redisTemplate;

    private DeviceService deviceService = new DeviceService();

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public AliResponse token(@RequestBody AliGenieRequest request) {
        System.out.println("request:" + request.getPayload().getAccessToken());
        System.out.println("namespace:" + request.getHeader().getNamespace());
        AliResponse token = new AliResponse();
        String accessToken = request.getPayload().getAccessToken();

        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setAccessToken(accessToken);
        UserOauth2 userResult = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2);
        if (userResult == null) {
            return token;
        }

        if (AliGenieNamespace.discovery.equalsIgnoreCase(request.getHeader().getNamespace())) {
            AliDevicesResponse aliDevicesResponse = new AliDevicesResponse();

            List<Device> deviceList = DeviceDataManager.getDeviceList(userResult.getUserId());

            TaobaoHeader header = new TaobaoHeader();
            header = request.getHeader();
            header.setName("DiscoveryDevicesResponse");
            aliDevicesResponse.setHeader(header);
            AliDevicesList aliDevicesList = new AliDevicesList();
            List<AliDevices> payloadList = new ArrayList<>();
            for (Device item : deviceList) {

                AliDevices payload = new AliDevices();
                payload.setDeviceId(item.getUserRelationId());
                payload.setBrand("smartplus");
                payload.setDeviceType("curtains");
                payload.setDeviceName(item.getName());
                payload.setIcon("www.baidu.com");

                List<Attributs> list = new ArrayList<>();
                Attributs attributs = new Attributs();
                attributs.setName("test");
                attributs.setValue("value");
                list.add(attributs);
                payload.setProperties(list);
                payloadList.add(payload);
            }


            aliDevicesList.setDevices(payloadList);
            aliDevicesResponse.setPayload(aliDevicesList);
            System.out.println("response:" + JSON.toJSONString(aliDevicesResponse));
            return aliDevicesResponse;
        } else if (AliGenieNamespace.control.equalsIgnoreCase(request.getHeader().getNamespace())) {
            List<Device> deviceListData = new ArrayList<>();
            Device device1 = new Device();
            device1.setEquipmentMac( request.getPayload().getExtensions().getDeviceMac() );
            device1.setEquipmentEp(request.getPayload().getExtensions().getDeviceEq());
            //device1.setId(request.getPayload());
            deviceListData.add(device1);
            boolean operateCmd = false;
            String cmdStr = "";
            if (operateCmd) {
                cmdStr = Constants.openCmd;
            } else {
                cmdStr = Constants.closeCmd;
            }

            deviceService.sendCmdToServer(deviceListData, cmdStr, userOauth2.getUserId() + "");
        }

        /*try {
            String result = IOUtils.toString(request.getInputStream(), "UTF-8");
            System.out.println("result:"+result);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return token;
    }
}
