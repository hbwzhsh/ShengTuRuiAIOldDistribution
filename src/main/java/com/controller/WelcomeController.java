package com.controller;

import com.init.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class WelcomeController {


    private static Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    // inject via application.properties


    @Value("${amazon.client_id}")
    private String client_id = "";

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String redirect_uri = request.getParameter("redirect_uri");
        String state = request.getParameter("state");
        session.setAttribute("redirect_uri", redirect_uri);

        System.out.println("redirect_uri:" + redirect_uri);

        session.setAttribute("state", state);
        model.put("client_id", client_id);
        return "login";
    }

    @RequestMapping("/relogin")
    public String welcome(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String redirect_uri = (String) session.getAttribute("redirect_uri");
        System.out.println("redirect_uri2222:" + redirect_uri);
        String state = (String) session.getAttribute("state");
        String code = request.getParameter("code");
        String newUrl = redirect_uri + "?state=" + state + "&code=" + code;
        System.out.println("newUrl:" + newUrl);
        model.put("newUrl", newUrl);
        //request.getRequestDispatcher(newUrl).forward(request, response);
        //response.sendRedirect(newUrl);
        return "reLogin";
    }


    @RequestMapping("/welcomeData")
    @ResponseBody
    public String welcomeData(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "reLogin";
    }


    @RequestMapping("/testLog")
    @ResponseBody
    public String testLog(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("test logg");
        return "reLogin";
    }


}