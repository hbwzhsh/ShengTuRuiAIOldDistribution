package com.intent.googleintent;

import com.SpringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.Device;
import com.bean.IntendParams;
import com.bean.google.*;
import com.bean.google.newpojo.command.request.DeviceExeRequest;
import com.bean.google.newpojo.command.request.Devices;
import com.bean.google.newpojo.command.request.Execution;
import com.bean.google.newpojo.command.request.Payload;
import com.bean.google.newpojo.command.response.CommandsResponse;
import com.bean.google.newpojo.command.response.DeviceExeResponse;
import com.bean.google.newpojo.command.response.PayloadResponse;
import com.bean.google.newpojo.command.response.StatesResponse;
import com.bean.google.newpojo.sync.*;
import com.bean.site.UserOauth2;
import com.intent.amazonintent.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GoogleDeviceProvidor {

    private static String devicesSYNC = "action.devices.SYNC";
    private static String devicesQUERY = "action.devices.QUERY";
    private static String devicesEXECUTE = "action.devices.EXECUTE";

    private DeviceService deviceService = new DeviceService();

    @RequestMapping(value = "/googledevices")
    public String token( HttpServletRequest request, HttpServletResponse response ) {
        try {
            System.out.println("Authorization:" + request.getHeader("Authorization"));
            String header[] = request.getHeader("Authorization").split("\\s+");
            System.out.println("header[0]------------------------》》》》》》》》》》》》》》》》》》》》:" + header[0]);

            String accessToken =  header[1];

            UserOauth2 userOauth2 = new UserOauth2();
            userOauth2.setAccessToken(accessToken);
            UserOauth2 userOauth2Reust = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2);

            if (userOauth2Reust != null) {

                String result = IOUtils.toString(request.getInputStream(), "UTF-8");
                GoogleRequest request1 = JSONObject.toJavaObject(JSONObject.parseObject(result), GoogleRequest.class);

                if (request1 != null) {
                    for (GoogleInputs inputs : request1.getInputs()) {
                        if (devicesSYNC.equalsIgnoreCase(inputs.getIntent())) {
                            List<Device> deviceList = SpringUtil.getUserMapper().getDeviceList(userOauth2Reust.getUserId() + "");
                            return requestSync( request1.getRequestId(), deviceList, userOauth2 );
                        } else if (devicesQUERY.equalsIgnoreCase(inputs.getIntent())) {
                            return devicesQuery(result, userOauth2);
                        } else if (devicesEXECUTE.equalsIgnoreCase(inputs.getIntent())) {
                            return deviceExecute(result, userOauth2);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String deviceExecute(String json, UserOauth2 userOauth2) {

        DeviceExeRequest deviceExeRequest = JSONObject.parseObject(json, DeviceExeRequest.class);

        List<Devices> googleCMDDevices = deviceExeRequest.getInputs().get(0).getPayload().getCommands().get(0).getDevices();
        List<Execution> executions =  deviceExeRequest.getInputs().get(0).getPayload().getCommands().get(0).getExecution();


        List<Device> deviceListData = new ArrayList<>();
        for(Devices device:googleCMDDevices){
            Device device1 = new Device();
            device1.setDeviceMac(device.getCustomData().getDeviceMac());
            device1.setEquipmentEp(device.getCustomData().getDeviceEq());
            deviceListData.add(device1);
        }

        boolean operateCmd = false;
        for(Execution execution:executions){
            operateCmd =   execution.getParams().getOn();
        }

        deviceService.sendCmdToServer(deviceListData,"0000",userOauth2.getUserId()+"");

        DeviceExeResponse deviceExeResponse = new DeviceExeResponse();
        CommandsResponse commands = new CommandsResponse();
        StatesResponse states = new StatesResponse();
        states.setOn(true);
        states.setOnline(true);

        commands.setIds(Arrays.asList("123"));
        commands.setStates(states);
        commands.setStatus("SUCCESS");
        List<CommandsResponse> commandsList = new ArrayList<>();

        commandsList.add(commands);

        PayloadResponse payload = new PayloadResponse();
        payload.setCommands(commandsList);


        deviceExeResponse.setPayload(payload);
        deviceExeResponse.setRequestId(deviceExeRequest.getRequestId());
        return JSONObject.toJSONString(deviceExeResponse);
    }


    private String devicesQuery(String json, UserOauth2 userOauth2) {
        String responseStr = "";
        System.out.println("|responseStr:" + responseStr);
        return responseStr;
    }


    private String requestSync(String requestId, List<Device> deviceList, UserOauth2 userOauth2) {
        String responseStr = googleDevicesDiscovery(requestId, deviceList).toJSONString();
        System.out.println("responseStr:" + responseStr);
        return responseStr;
    }

    public static void main(String[] args) {
//        System.out.println(googleDevicesDiscovery("123").toJSONString());
    }

    private static JSONObject googleDevicesDiscovery(String requestId, List<Device> deviceObjList) {
        JSONObject response = new JSONObject();
        response.put("requestId", requestId);
        JSONArray deviceList = new JSONArray();

        DeviceSyncResponse response1 = new DeviceSyncResponse();
        PayloadSync payload  = new PayloadSync();
        List<DevicesSync> devices = new ArrayList<>();

        for (Device deviceObj : deviceObjList) {
            DevicesSync devicesSync = new DevicesSync();
            CustomDataSync customDataSync = new CustomDataSync();
            customDataSync.setDeviceEq("");
            customDataSync.setDeviceMac("");

            DeviceInfoSync deviceInfoSync = new DeviceInfoSync();
            deviceInfoSync.setHwVersion("");
            deviceInfoSync.setManufacturer("");
            deviceInfoSync.setModel("");
            deviceInfoSync.setSwVersion("");

            NameSync nameSync = new NameSync();
            nameSync.setDefaultNames(Arrays.asList("123"));
            nameSync.setName("");
            nameSync.setNicknames(Arrays.asList("123"));

            devicesSync.setId("123");
            devicesSync.setWillReportState(false);
            devicesSync.setType("action.devices.types.LIGHT");
            devicesSync.setTraits(Arrays.asList("action.devices.traits.OnOff", "action.devices.traits.Brightness"));
            devicesSync.setName(nameSync);
            devicesSync.setDeviceInfo(deviceInfoSync);
            devicesSync.setCustomData(customDataSync);
            devices.add(devicesSync);
        }

        payload.setDevices(devices);
        payload.setAgentUserId("1836.15267389");

        response1.setRequestId(requestId);
        response1.setPayload(payload);

        return response;
    }

}
