package com.intent.googleintent;

import com.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.Device;
import com.bean.site.UserOauth2;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class GoogleDeviceProvidor {

    @RequestMapping(value = "/googledevices")
    public String token(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Authorization:" + request.getHeader("Authorization"));
            String accessToken = request.getHeader("Authorization");

            UserOauth2 userOauth2 = new UserOauth2();
            userOauth2.setAccessToken(accessToken);
            UserOauth2 userOauth2Reust = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2);
            if (userOauth2Reust != null) {
                List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(userOauth2Reust.getUserId() + "");
                String result = IOUtils.toString(request.getInputStream(), "UTF-8");
                JSONObject googleRequst = JSON.parseObject(result);
                if (googleRequst != null) {
                    String requestId = (String) googleRequst.get("requestId");
                    String responseStr = googleDevicesDiscovery(requestId,deviceList).toJSONString();
                    System.out.println("|responseStr:" + responseStr);
                    return responseStr;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {

//        System.out.println(googleDevicesDiscovery("123").toJSONString());
    }

    private static JSONObject googleDevicesDiscovery(String requestId, List<Device> deviceObjList) {
        JSONObject response = new JSONObject();
        response.put("requestId", requestId);
        JSONArray deviceList = new JSONArray();

        for (Device deviceObj : deviceObjList) {
            JSONObject device = new JSONObject();
            JSONArray traits = new JSONArray();
            traits.add("action.devices.traits.OnOff");
            JSONObject name = new JSONObject();
            JSONArray defaultNames = new JSONArray();
            defaultNames.add(deviceObj.getName());
            JSONArray nicknames = new JSONArray();
            nicknames.add(deviceObj.getName());
            name.put("defaultNames", defaultNames);
            name.put("nicknames", nicknames);
            name.put("name", deviceObj.getName());

            JSONObject deviceInfo = new JSONObject();

            deviceInfo.put("manufacturer", "smartplus");
            deviceInfo.put("model", "hs1234");
            deviceInfo.put("hwVersion", "3.2");
            deviceInfo.put("swVersion", "11.4");

            JSONObject customData = new JSONObject();

            customData.put("fooValue", 74);
            customData.put("barValue", true);
            customData.put("bazValue", "foo");

            device.put("id", deviceObj.getDeviceMac());
            device.put("type", "action.devices.types.OUTLET");
            device.put("traits", traits);

            device.put("name", name);
            device.put("willReportState", false);
            device.put("deviceInfo", deviceInfo);
            device.put("customData", customData);
            deviceList.add(device);
        }
        JSONObject payload = new JSONObject();
        payload.put("agentUserId", "1836.15267389");
        payload.put("devices", deviceList);


        response.put("payload", payload);
        return response;
    }

}
