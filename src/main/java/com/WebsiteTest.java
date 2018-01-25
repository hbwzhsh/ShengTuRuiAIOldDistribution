package com;

import com.bean.Device;

import com.bean.site.UserOauth2;
import com.service.DeviceService;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteTest {


    @Test
    public void test(){
        DeviceService deviceService = new DeviceService();

        List<Device> deviceListData = new ArrayList<>();
        Device device1 = new Device();
        device1.setHostMac("9C65F9209CE8");
        device1.setEquipmentMac("7bff6314004b1200");
        device1.setEquipmentEp("00");
        deviceListData.add(device1);
        String cmdStr = "00";


        //7bff6314004b1200-00-00-0000

        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setUserId("10132");

        SoketClient client = new SoketClient();
        SocketFactory.socketConnections.put(userOauth2.getUserId(), client);

        client.connectService(userOauth2.getUserId());

        deviceService.sendCmdToServer(deviceListData, cmdStr, userOauth2.getUserId(),client);
    }
}