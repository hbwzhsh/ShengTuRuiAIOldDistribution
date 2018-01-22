package com.service;

import com.bean.User;
import com.datasource.DbDAO;
import com.init.Constants;
import com.init.ConstantsMethod;
import com.intent.amazonintent.DeviceService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final Logger logger = LogManager.getLogger("mylog");
	// private static final Logger logger =
	// LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

	DeviceService service = new DeviceService();
	DbDAO dbDAO = new DbDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		final String accessToken = request.getParameter("access_token");

		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(passWord) && StringUtils.isNotBlank(accessToken)) {

			final User user = dbDAO.queryUserByUserName(userName.trim(), passWord.trim());
			if (user != null) {

				Runnable runnable = () -> {
					dbDAO.updateUserByUserName(userName, accessToken);
					service.refreshCacheData(accessToken);

					ConstantsMethod.addDefualRoom(accessToken, Constants.commonDefualtRoom);
				};
				Thread thread = new Thread(runnable);
				thread.start();

				response.getWriter().print("success");
				return;
			}
		}
		response.getWriter().print("your username or password is wrong!");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
