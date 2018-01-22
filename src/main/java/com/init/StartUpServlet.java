package com.init;

import com.netty.NettyClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		new NettyClient();
		System.out.println("StartUpServlet---->");
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
