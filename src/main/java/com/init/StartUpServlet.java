package com.init;

import com.SpringUtil;
import com.bean.Device;
import com.bean.UsersTemp;
import com.socket.SocketFactory;
import com.socket.SoketClient;
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
        
        ConstantsMethod.initData();

        //new SoketClient().connectService("10132" );
		//fullfillData();

    }


    private void fullfillData(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try{
					List<UsersTemp> usersTempList = SpringUtil.getUserMapper().getUsersTemp();
					for(UsersTemp users : usersTempList){
						int countNumber = 	SpringUtil.getUserMapper().getDeviceWithNoNameList(users.getUserId()+"");
						if(countNumber > 0){

							SoketClient tempClient  = SocketFactory.socketConnections.get(users.getUserId()+"");
							if(tempClient != null){
								boolean isConnected = tempClient.getSendsession().isConnected();
								if(!isConnected){
									SocketFactory.socketConnections.remove(users.getUserId());
									tempClient =null;
								}
							}

							if(tempClient == null){
								SoketClient client = 	new SoketClient();
								SocketFactory.socketConnections.put( users.getUserId()+"",client);
								client.connectService(users.getUserId()+"");

							}else{
								tempClient.getDevicesFromService(users.getUserId() + "");
							}
						}
					}
				}catch (Exception e){
					e.printStackTrace();
				}

			}
		}, 5000, 20000000);
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
