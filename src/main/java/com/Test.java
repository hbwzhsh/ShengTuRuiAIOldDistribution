package com;

import com.bean.Device;
import com.socket.SocketFactory;

import java.util.HashMap;

public class Test {

    @org.junit.Test
    public void testHashMap(){

        Device device = new Device();
        device.setEquipmentEp("123");
        HashMap map = new HashMap();
        map.put("123", device);
        device.setEquipmentEp("345");
        Device device1 = (Device) map.get("123");
        System.out.println(device1.getEquipmentEp());


    }
}
