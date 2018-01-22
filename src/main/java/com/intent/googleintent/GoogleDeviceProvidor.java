package com.intent.googleintent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class GoogleDeviceProvidor {

    @RequestMapping(value = "/googledevices")
    public String token(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Authorization:" + request.getHeader("Authorization"));

            String result = IOUtils.toString(request.getInputStream(), "UTF-8");
            JSONObject googleRequst = JSON.parseObject(result);
            if(googleRequst!= null){
                String requestId =(String) googleRequst.get("requestId");
                String responseStr = googleDevicesDiscovery(requestId).toJSONString();
                System.out.println("|responseStr:"+responseStr);
                return responseStr;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println( googleDevicesDiscovery("123").toJSONString() );
    }
    private static JSONObject googleDevicesDiscovery(String requestId) {
        JSONObject response = new JSONObject();
        response.put("requestId",requestId);
        JSONArray deviceList = new JSONArray();
        JSONObject device = new JSONObject();
        JSONArray traits = new JSONArray();
        traits.add("action.devices.traits.OnOff");
        JSONObject name = new JSONObject();
        JSONArray defaultNames = new JSONArray();
        defaultNames.add("My Outlet 1234");
        JSONArray nicknames = new JSONArray();
        nicknames.add("wall plug");
        name.put("defaultNames",defaultNames);
        name.put("nicknames",nicknames);
        name.put("name","Night light");

        JSONObject deviceInfo = new JSONObject();

        deviceInfo.put("manufacturer","lights-out-inc");
        deviceInfo.put("model","hs1234");
        deviceInfo.put("hwVersion","3.2");
        deviceInfo.put("swVersion","11.4");

        JSONObject customData = new JSONObject();

        customData.put("fooValue",74);
        customData.put("barValue",true);
        customData.put("bazValue","foo");

        device.put("id","123");
        device.put("type","action.devices.types.OUTLET");
        device.put("traits",traits);

        device.put("name",name);
        device.put("willReportState",false);
        device.put("deviceInfo",deviceInfo);
        device.put("customData",customData);


        deviceList.add(device);

        JSONObject payload = new JSONObject();
        payload.put("agentUserId","1836.15267389");
        payload.put("devices",deviceList);


        response.put("payload",payload);
        return response;
    }

}
