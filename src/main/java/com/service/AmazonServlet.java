package com.service;

import com.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.bean.site.UserSite;
import com.intent.amazonintent.DeviceService;
import com.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AmazonServlet
 */
@WebServlet("/AmazonServlet")
@Component
public class AmazonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	DeviceService service = new DeviceService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmazonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	   String code = request.getParameter("code");
	   System.out.println("code:"+code);
	   JSONObject json = new JSONObject();
	   if( StringUtils.isNotBlank(code) ){
		   String amazonUserEmail =  AmazonService.getAmazonUserId(code);
		   System.out.println("amazonUserEmail:"+amazonUserEmail);
		   UserSite userTemp = new UserSite();
		   userTemp.setEmail(amazonUserEmail);
		   UserSite userObj = SpringUtil.getUserMapper().getObjectByCondition(userTemp);
		   
		   json.put("value", amazonUserEmail);
		   
		   if(userObj != null ){
			   System.out.println("userObj:"+userObj);
			   //dbDAO.updateUserByUserName( userObj.getUserName(), amazonUserEmail );
			   
			   service.refreshCacheData(amazonUserEmail);
			   json.put("flag", 1);
			   response.getWriter().print(json.toJSONString());
			   return;
		   }
	   }
	   
	   json.put("flag", 0);
	   response.getWriter().print(json.toJSONString());
	   return;
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	   String amazonUserId = request.getParameter("amazonUserId");
	   String amazonUserEmail = request.getParameter("amazonUserEmail");
	   
	   if( StringUtils.isNotBlank(amazonUserId) ||  StringUtils.isNotBlank(amazonUserEmail) ){
		   User user = new User();
		   user.setEmail(amazonUserEmail);
		   User userObj = dbDAO.getUserByConditions(user);
		   
		   if(userObj != null ){
			   dbDAO.updateUserByUserName( userObj.getUserName(), amazonUserId );
			   
			   service.refreshCacheData(amazonUserId);
			   
			   response.getWriter().print(1);
			   return;
		   }
	   }
	   
	   response.getWriter().print(0);
	   return;
	}
*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
