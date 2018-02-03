package com.init;

import com.SpringUtil;
import com.bean.SpeakerUsers;
import com.data.DeviceDataManager;
import com.socket.SocketFactory;
import com.socket.SoketClient;
import com.utility.ConstantsMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Servlet implementation class StartUpServlet
 */

@WebServlet(urlPatterns={"/StartUpServlet"},loadOnStartup=1)
public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LogManager.getLogger(StartUpServlet.class);
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartUpServlet() {
        super();
        
        logger.debug("Initialization.....");
        logger.trace("Entering application.");
        logger.error("Didn't do it.");
        logger.trace("Exiting application.");
        logger.debug("12312312231231223");
        
        //ConstantsMethod.initData();

		new DeviceDataManager().updateDataSchedule();

    }




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//new testLogicFlow().testrefreshCacheData();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
