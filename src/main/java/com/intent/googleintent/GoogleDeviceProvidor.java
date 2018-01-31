package com.intent.googleintent;
import com.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bean.Device;
import com.bean.google.*;
import com.bean.google.newpojo.command.request.DeviceExeRequest;
import com.bean.google.newpojo.command.request.Devices;
import com.bean.google.newpojo.command.request.Execution;
import com.bean.google.newpojo.command.response.CommandsResponse;
import com.bean.google.newpojo.command.response.DeviceExeResponse;
import com.bean.google.newpojo.command.response.PayloadResponse;
import com.bean.google.newpojo.command.response.StatesResponse;
import com.bean.google.newpojo.sync.*;
import com.bean.site.UserOauth2;
import com.data.DeviceDataManager;
import com.utility.Constants;
import com.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GoogleDeviceProvidor {

    private static String devicesSYNC = "action.devices.SYNC";
    private static String devicesQUERY = "action.devices.QUERY";
    private static String devicesEXECUTE = "action.devices.EXECUTE";

    private DeviceService deviceService;

    @RequestMapping(value = "/googledevices")
    public GoogleRequestParent token(HttpServletRequest request, HttpServletResponse response) {
        try {
            deviceService = new DeviceService();
            System.out.println("Authorization:" + request.getHeader("Authorization"));
            String header[] = request.getHeader("Authorization").split("\\s+");
            System.out.println("header[0]------------------------》》》》》》》》》》》》》》》》》》》》:" + header[0]);

            String accessToken = header[1];

            UserOauth2 userOauth2param = new UserOauth2();
            userOauth2param.setAccessToken(accessToken);
            UserOauth2 userOauth2Reust = SpringUtil.getUserMapper().getOauth2ByCondition(userOauth2param);

            if (userOauth2Reust != null) {

                String result = IOUtils.toString( request.getInputStream(), "UTF-8" );
                System.out.println("request from google :" + result);

                GoogleRequest request1 = JSONObject.toJavaObject(JSONObject.parseObject(result), GoogleRequest.class);

                if (request1 != null) {
                    for (GoogleInputs inputs : request1.getInputs()) {
                        if (devicesSYNC.equalsIgnoreCase(inputs.getIntent())) {
                            List<Device> deviceList = DeviceDataManager.getDeviceList(userOauth2Reust.getUserId());
                            System.out.println("deviceList:-------------------->" + deviceList.size());
                            return googleDevicesDiscovery(request1.getRequestId(), deviceList);
                        } else if (devicesQUERY.equalsIgnoreCase(inputs.getIntent())) {
                        } else if (devicesEXECUTE.equalsIgnoreCase(inputs.getIntent())) {
                            return deviceExecute(result, userOauth2Reust);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DeviceSyncResponse();
    }

    private GoogleRequestParent deviceExecute(String json, UserOauth2 userOauth2) {

        DeviceExeRequest deviceExeRequest = JSONObject.parseObject(json, DeviceExeRequest.class);

        List<Devices> googleCMDDevices = deviceExeRequest.getInputs().get(0).getPayload().getCommands().get(0).getDevices();
        List<Execution> executions = deviceExeRequest.getInputs().get(0).getPayload().getCommands().get(0).getExecution();

        List<Device> deviceListData = new ArrayList<>();
        for (Devices device : googleCMDDevices) {
            Device device1 = new Device();
            device1.setEquipmentMac(device.getCustomData().getEquipmentMac());
            device1.setEquipmentEp(device.getCustomData().getDeviceEq());
            device1.setUserRelationId(device.getId());
            device1.setHostMac(device.getCustomData().getHostMac());
            deviceListData.add(device1);
        }

        boolean operateCmd = false;
        for (Execution execution : executions) {
            operateCmd = execution.getParams().getOn();
        }
        String cmdStr = "";
        if (operateCmd) {
            cmdStr = Constants.openCmd;
        } else {
            cmdStr = Constants.closeCmd;
        }

        System.out.println("------------------>sending command.....");
        deviceService.sendCmdToServer(deviceListData, cmdStr, userOauth2.getUserId());


        DeviceExeResponse deviceExeResponse = new DeviceExeResponse();
        CommandsResponse commands = new CommandsResponse();
        StatesResponse states = new StatesResponse();
        states.setOn(true);
        states.setOnline(true);

        List<String> ids = deviceListData.stream().map(x -> x.getUserRelationId()).collect(Collectors.toList());
        commands.setIds(ids);
        commands.setStates(states);
        commands.setStatus("SUCCESS");
        List<CommandsResponse> commandsList = new ArrayList<>();

        commandsList.add(commands);

        PayloadResponse payload = new PayloadResponse();
        payload.setCommands(commandsList);
        deviceExeResponse.setPayload(payload);
        deviceExeResponse.setRequestId(deviceExeRequest.getRequestId());
        return deviceExeResponse;
    }


    private String requestSync(String requestId, List<Device> deviceList, UserOauth2 userOauth2) {
        return JSONObject.toJSONString(googleDevicesDiscovery(requestId, deviceList));
    }


    private static DeviceSyncResponse googleDevicesDiscovery(String requestId, List<Device> deviceObjList) {
        DeviceSyncResponse response1 = new DeviceSyncResponse();
        PayloadSync payload = new PayloadSync();
        List<DevicesSync> devices = new ArrayList<>();

        for (Device deviceObj : deviceObjList) {
            DevicesSync devicesSync = new DevicesSync();
            CustomDataSync customDataSync = new CustomDataSync();
            customDataSync.setDeviceEq(deviceObj.getEquipmentEp());
            customDataSync.setEquipmentMac(deviceObj.getEquipmentMac());
            customDataSync.setHostMac(deviceObj.getHostMac());

            DeviceInfoSync deviceInfoSync = new DeviceInfoSync();
            deviceInfoSync.setHwVersion("1.0");
            deviceInfoSync.setManufacturer("smartplus");
            deviceInfoSync.setModel("hs1234");
            deviceInfoSync.setSwVersion("1.0");

            NameSync nameSync = new NameSync();
            if (StringUtils.isBlank(deviceObj.getName())) {
                deviceObj.setName("curtain");
            }

            nameSync.setDefaultNames(Arrays.asList(deviceObj.getName()));
            nameSync.setName(deviceObj.getName());
            nameSync.setNicknames(Arrays.asList(deviceObj.getName()));


            devicesSync.setId(deviceObj.getUserRelationId());
            devicesSync.setType("action.devices.types.OUTLET");
            devicesSync.setTraits(Arrays.asList("action.devices.traits.OnOff", "action.devices.traits.Brightness"));
            devicesSync.setName(nameSync);
            devicesSync.setWillReportState(false);


            devicesSync.setDeviceInfo(deviceInfoSync);
            devicesSync.setCustomData(customDataSync);
            devices.add(devicesSync);
        }

        payload.setDevices(devices);
        payload.setAgentUserId("1836.15267389");

        response1.setRequestId(requestId);
        response1.setPayload(payload);

        System.out.println(JSON.toJSONString(response1));

        return response1;
    }

}
