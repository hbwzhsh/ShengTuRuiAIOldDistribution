package com.mapper;
import com.bean.Device;
import com.bean.UserHostRelation;
import com.bean.UsersTemp;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;

import java.util.List;

public interface UserMapper {

    public UserSite getObjectByCondition(UserSite userSite);

    public int addObjectToOauth2(UserOauth2 userOauth2);
    public UserOauth2 getOauth2ByCondition(UserOauth2 userOauth2);

    public int updateHouseRelation(Device device);
    public List<UserHostRelation> getUserHostRelation(UserHostRelation userHostRelation);
    public List<Device> getDeviceList(String userId);

    public List<UsersTemp> getUsersTemp();

    public int addUserTemp(UsersTemp usersTemp);


    public int getDeviceWithNoNameList(String userId);



}