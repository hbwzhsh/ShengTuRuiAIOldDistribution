package com.intent.taobaointent;

import com.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.bean.Device;
import com.bean.site.UserOauth2;
import com.bean.taobao.*;
import com.data.RedisDAO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class TaobaoDeviceProvidor {

    @RequestMapping(value = "/devices", method = RequestMethod.POST)
    public AliResponse token(@RequestBody AliGenieRequest request) {
        System.out.println("request:"+request.getPayload().getAccessToken());
        System.out.println("namespace:"+request.getHeader().getNamespace());
        AliResponse token = new AliResponse();
        String accessToken = request.getPayload().getAccessToken();

        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setAccessToken(accessToken);
        UserOauth2 userResult = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2);
        if(userResult == null){
            return token;
        }

        if (AliGenieNamespace.discovery.equalsIgnoreCase(request.getHeader().getNamespace())) {
            AliDevicesResponse aliDevicesResponse = new AliDevicesResponse();

            List<Device> deviceList = RedisDAO.getObject(userResult.getUserId());

            TaobaoHeader header = new TaobaoHeader();
            header = request.getHeader();
            header.setName("DiscoveryDevicesResponse");
            aliDevicesResponse.setHeader(header);
            AliDevicesList aliDevicesList = new AliDevicesList();
            List<AliDevices> payloadList = new ArrayList<>();
            for(Device item: deviceList){

                AliDevices payload = new AliDevices();
                payload.setDeviceId(item.getId());
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
            System.out.println("response:"+ JSON.toJSONString(aliDevicesResponse));
            return aliDevicesResponse;
        } else if (AliGenieNamespace.control.equalsIgnoreCase(request.getHeader().getNamespace())) {

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
