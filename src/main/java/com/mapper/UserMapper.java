package com.mapper;


import com.bean.site.UserOauth2;
import com.bean.site.UserSite;

public interface UserMapper {

    public UserSite getObjectByUserName(String userName);

    public int addObjectToOauth2(UserOauth2 userOauth2);

    public UserOauth2 getOauth2ByCondition(UserOauth2 userOauth2);


}