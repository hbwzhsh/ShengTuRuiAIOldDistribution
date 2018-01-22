package com.intent.taobaointent;

import com.alibaba.fastjson.JSON;
import com.bean.taobao.*;
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
        if (AliGenieNamespace.discovery.equalsIgnoreCase(request.getHeader().getNamespace())) {
            AliDevicesResponse aliDevicesResponse = new AliDevicesResponse();
            Header header = new Header();
            header = request.getHeader();
            header.setName("DiscoveryDevicesResponse");
            String accessToken = request.getPayload().getAccessToken();
            System.out.println("accessToken:"+request.getPayload().getAccessToken());
            aliDevicesResponse.setHeader(header);

            AliDevicesList aliDevicesList = new AliDevicesList();
            List<AliDevices> payloadList = new ArrayList<>();
            AliDevices payload = new AliDevices();
            payload.setDeviceId(new Random().nextInt(1000) + "");
            payload.setBrand("smartplus");
            payload.setDeviceType("curtains");
            payload.setDeviceName("mycurtains");
            payload.setIcon("www.baidu.com");


            List<Attributs> list = new ArrayList<>();
            Attributs attributs = new Attributs();
            attributs.setName("test");
            attributs.setValue("value");
            list.add(attributs);
            payload.setProperties(list);
            payloadList.add(payload);
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
