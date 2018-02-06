package com.oauth2;

import com.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.bean.site.UserOauth2;
import com.mapper.UserMapper;
import com.oauth2.model.AccessData;
import com.oauth2.model.AccessToken;
import com.oauth2.model.ErrorToken;
import com.oauth2.model.TokenInfo;
import com.utility.Config;
import com.utility.TokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TokenProvidor {


    @Autowired
    Config config;

    /**
     * https://XXXXX/token?grant_type=authorization_code&client_id=XXXXX&client_secret=XXXXXX&code=XXXXXXXX&redirect_uri=https%3A%2F%2Fopen.bot.tmall.com%2Foauth%2Fcallback
     *
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public TokenInfo token(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        String grant_type = request.getParameter("grant_type");
        String client_id = request.getParameter("client_id");
        String client_secret = request.getParameter("client_secret");
        String redirect_uri = request.getParameter("redirect_uri");

        System.out.println("grant_type:" + grant_type);
        System.out.println("client_id:" + client_id);
        System.out.println("client_secret:" + client_secret);
        System.out.println("redirect_uri:" + redirect_uri);

//        boolean params = StringUtils.isEmpty(grant_type) || !(config.getClientId().equalsIgnoreCase(client_id));
        boolean params = StringUtils.isEmpty(grant_type);

        System.out.println("params:" + params);
        if (params) {
            ErrorToken token = new ErrorToken();
            token.setError("errorCode");
            token.setError_description("errorCode description");
            return token;
        }

        if ("authorization_code".equalsIgnoreCase(grant_type)) {
            String code = request.getParameter("code");
            System.out.println("code:" + code);
            UserOauth2 oauth2 = new UserOauth2();
            oauth2.setCode(code);
            TokenInfo token = getTokenInfo(oauth2);
            if (token != null){
               /* AccessData tokenOld = AccessDataFactory.list.get(0);
                AccessToken accessToken = new AccessToken();
                accessToken.setExpires_in(tokenOld.getExpires_in());
                accessToken.setAccess_token(tokenOld.getAccess_token());
                accessToken.setRefresh_token(tokenOld.getRefresh_token());
                accessToken.setToken_type("bearer");*/
                System.out.println("tokenJSON:"+ JSONObject.toJSONString(token));
                return token;
            }
        } else if ("refresh_token".equalsIgnoreCase(grant_type)) {
            String refresh_token = request.getParameter("refresh_token");
            UserOauth2 oauth2 = new UserOauth2();
            oauth2.setRefreshToken(refresh_token);
            TokenInfo token = getTokenInfo(oauth2);
            if (token != null) return token;
        }
        ErrorToken token = new ErrorToken();
        token.setError("errorCode");
        token.setError_description("errorCode description");
        System.out.println("tokenJSON:"+ JSONObject.toJSONString(token));
        return token;
    }

    private TokenInfo getTokenInfo(UserOauth2 oauth2) {
        UserOauth2 result = SpringUtil.getUserMapper().getOauth2ByCondition(oauth2);
        if (result != null) {
            AccessToken token = new AccessToken();
            token.setAccess_token(result.getAccessToken());
            token.setRefresh_token(result.getRefreshToken());
            token.setExpires_in(TokenFactory.expires_in);
            token.setToken_type("bearer");
            return token;
        }
        return null;
    }
}
