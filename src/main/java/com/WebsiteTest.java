package com;

import com.bean.Device;

import com.bean.site.UserOauth2;
import com.service.DeviceService;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsiteTest {




    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        UserOauth2 user=new UserOauth2();
        user.setAccessToken("123123");
        user.setUserId("123");
        ValueOperations<String, UserOauth2> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neox");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }


    @Test
    public void test1(){
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

        deviceService.sendCmdToServer(deviceListData, cmdStr, userOauth2.getUserId());
    }
}