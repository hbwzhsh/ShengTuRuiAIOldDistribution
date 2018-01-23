package com.oauth2;

import com.SpringUtil;
import com.bean.UsersTemp;
import com.bean.site.UserOauth2;
import com.bean.site.UserSite;
import com.intent.amazonintent.DeviceService;
import com.oauth2.model.ResponseMsg;
import com.utility.MD5Util;
import com.utility.TokenFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthClient {



    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        System.out.println(redirect_uri);
        map.put("redirect_uri", redirect_uri);
        map.put("state", state);
        return "/oauth/login";
    }


    @RequestMapping(value = "/loginGoogle")
    public String loginGoogle(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        System.out.println(redirect_uri);
        map.put("redirect_uri", redirect_uri);
        map.put("state", state);
        return "loginGoogle";
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
        UserSite user = SpringUtil.getUserMapper().getObjectByCondition( userSite );
        if (user == null) {
            responseMsg.setData("this user isn't exist!");
            return responseMsg;
        }

        if (!MD5Util.JM(MD5Util.MD5(password)).equalsIgnoreCase(user.getPassword())) {
            responseMsg.setData("the password is wrong!");
            return responseMsg;
        }
        UserOauth2 userOauth2 = new UserOauth2();
        userOauth2.setAccessToken( TokenFactory.createAccessToken() );
        userOauth2.setRefreshToken( TokenFactory.createRefreshToken() );
        userOauth2.setCode( TokenFactory.createCode() );
        userOauth2.setUserId(Integer.parseInt(user.getUserId()));

        int count  = SpringUtil.getUserMapper().addObjectToOauth2(userOauth2);
        if(count == 0){
            responseMsg.setData("something wrong!");
            return responseMsg;
        }else{
            if(redirect_uri.indexOf("google")!=-1){
                responseMsg.setData(redirect_uri + "?state="+state+"&code=" + userOauth2.getCode());
            }else{
                responseMsg.setData(redirect_uri + "&state=" + state + "&code=" + userOauth2.getCode());
            }

            //new DeviceService().createSocketSession( user.getUserId() );

            UsersTemp temp = new UsersTemp();
            temp.setUserId(Integer.parseInt(user.getUserId()));
            SpringUtil.getUserMapper().addUserTemp(temp);

            responseMsg.setSuccessStatus();
            return responseMsg;
        }
    }


    @RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
    public void loginSubmit(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        System.out.println("loginSubmit:" + redirect_uri);
        if ("admin".equalsIgnoreCase(userName) && "admin".equalsIgnoreCase(password)) {
            try {
                String code = "123456code";
                response.sendRedirect(redirect_uri + "&state=" + state + "&code=" + code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("test....");
        }
    }



}
