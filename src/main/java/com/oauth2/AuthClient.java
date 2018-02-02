package com.oauth2;

import com.SpringUtil;
import com.bean.SpeakerUsers;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;
import com.data.DeviceDataManager;
import com.oauth2.model.ResponseMsg;
import com.utility.Config;
import com.utility.MD5Util;
import com.utility.TokenFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        map.put("redirect_uri", redirect_uri);
        map.put("state", state);
        logger.debug("login....");
        return "/oauth/login";
    }


    @RequestMapping(value = "/loginSubmitGoogle", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMsg loginSubmitGoogle(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");

        ResponseMsg responseMsg = new ResponseMsg();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            responseMsg.setData("userName or password is empty!");
            return responseMsg;
        }

        UserSite userSite = new UserSite();
        userSite.setUserName(userName);
        UserSite user = SpringUtil.getUserMapper().getObjectByCondition(userSite);
        if (user == null) {
            responseMsg.setData("this user isn't exist!");
            return responseMsg;
        }

        if (!MD5Util.JM(MD5Util.MD5(password)).equalsIgnoreCase(user.getPassword())) {
            responseMsg.setData("the password is wrong!");
            return responseMsg;
        }


        UserOauth2 oauth2Param = new UserOauth2();
        oauth2Param.setUserId(user.getUserId());
        UserOauth2 userOauthResult = SpringUtil.getUserMapper().getOauth2ByCondition(oauth2Param);

        if (userOauthResult != null) {
            getRightLink(redirect_uri, state, responseMsg, userOauthResult);
        } else {

            UserOauth2 userOauthParam = new UserOauth2();
            userOauthParam.setAccessToken(TokenFactory.createAccessToken());
            userOauthParam.setRefreshToken(TokenFactory.createRefreshToken());
            userOauthParam.setCode(TokenFactory.createCode());

            int count = SpringUtil.getUserMapper().addObjectToOauth2(userOauthParam);

            if (count == 0) {
                responseMsg.setData("something wrong!");
                return responseMsg;
            } else {
                getRightLink(redirect_uri, state, responseMsg, userOauthParam);

                SpeakerUsers temp = new SpeakerUsers();
                temp.setUserId(user.getUserId());
                SpringUtil.getUserMapper().addUserTemp(temp);
                //DeviceDataManager.cleanSocketClient(user.getUserId());
            }
        }
        responseMsg.setSuccessStatus();
        return responseMsg;
    }

    private void getRightLink(String redirect_uri, String state, ResponseMsg responseMsg, UserOauth2 userOauthResult) {
        if (redirect_uri.indexOf("tmall") == -1) {
            responseMsg.setData(redirect_uri + "?state=" + state + "&code=" + userOauthResult.getCode());
        } else {
            responseMsg.setData(redirect_uri + "&state=" + state + "&code=" + userOauthResult.getCode());
        }
    }

}
