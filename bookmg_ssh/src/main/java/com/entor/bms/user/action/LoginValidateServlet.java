package com.entor.bms.user.action;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.entor.bms.user.entity.UserInfo;
import com.entor.bms.user.service.UserService;
import com.entor.bms.user.service.impl.UserServiceImpl;

@WebServlet("/LoginValidateServlet")
public class LoginValidateServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger();

	private UserService userService;

	public LoginValidateServlet() {
		userService = new UserServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		LOGGER.info("service() -> LoginValidateServlet's service invoked!");
		super.service(arg0, arg1);
	}

	@Override
	public void destroy() {
		LOGGER.info("destroy() -> LoginValidateServlet's destroy invoked!");
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		LOGGER.info("init() -> LoginValidateServlet's init invoked!");
		super.init(config);
	}

	// 处理get请求
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("doGet() -> LoginValidateServlet's doGet invoked!");
		response.sendRedirect("login.jsp?login_msg=please login!");
	}

	// 处理post请求
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("doPost() -> LoginValidateServlet's doPost invoked!");
		// 获取请求的参数
		// 当以post方式请求时，不会再uri中携带参数，而是在请求的报文中
		// post方式提交的参数，在获取之前需要设置编码
		request.setCharacterEncoding("utf-8");
		String idCard = request.getParameter("idCard");
		String password = request.getParameter("password");

		// 设置响应的格式是text/html，编码为utf-8
		response.setContentType("text/html;charset=utf-8");

		// 获取完整的上下文路径
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";

		LOGGER.info("doPost() -> idCard = {}", idCard);
		LOGGER.info("doPost() -> password = {}", password);
		LOGGER.info("doPost() -> basePath = {}", basePath);

		// 验证用户名和密码
		int num = userService.loginValidate(idCard, password);
		if (num == -1) {
			// 请求转发到login.jsp
			request.setAttribute("login_msg", "用户名/密码不匹配");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if (num == 0) {
			// 请求转发到login.jsp
			request.setAttribute("login_msg", "用户名不存在");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		// 重定向，携带用户信息
		UserInfo user = userService.getUserInfoByIdCard(idCard);
		// 将user信息放到session中，在回话期间都可以访问到
		request.getSession().setAttribute("user", user);
		response.sendRedirect(basePath + "home.jsp");
	}
}
