package com.entor.bms.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger();

	private static String[] ingoreURI = { "/login", "/css", "/js", "/img", "/UserServlet", "/LoginValidateServlet",
			"/ErrorHandlerServlet" };

	public LoginFilter() {
	}

	@Override
	public void destroy() {
		LOGGER.info("destroy() -> LoginFilter's destroy invoked!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("doFilter() -> LoginFilter's doFilter invoked!");
		// 获取当前请求对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		LOGGER.info("doFilter() -> request uri = {}", req.getRequestURI());

		int flag = 0; // 标记当前是否存在不被忽略uri
		for (String ignore : ingoreURI) {
			if (req.getRequestURI().contains(ignore)) {
				flag = 1;// 当前uri被忽略
				break;
			}
		}

		// 如果想通过欢迎页访问，将标记置为0
		if (req.getRequestURI().equals("/bookmg_JSP/"))
			flag = 1;

		// 忽略
		if (flag == 0 && req.getSession().getAttribute("user") == null) {
			// 不忽略
			resp.setContentType("text/html;charset=utf-8");
			resp.sendRedirect(req.getContextPath() + "/login.jsp?login_msg=please login!");
		} else {
			System.out.println("do chain！！！！");
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("init() -> LoginFilter's init invoked!");
	}

}
