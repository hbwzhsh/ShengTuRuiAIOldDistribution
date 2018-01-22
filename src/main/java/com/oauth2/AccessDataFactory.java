package com.oauth2;

import com.oauth2.model.AccessData;

import java.util.ArrayList;
import java.util.List;

public class AccessDataFactory {

    static List<AccessData> list = new ArrayList<AccessData>();
    static {
        AccessData data = new AccessData();
        data.setAccess_token("123456access_token");
        data.setRefresh_token("123456refresh_token");
        data.setCode("123456code");
        data.setExpires_in(60000);
        list.add(data);
    }

}
