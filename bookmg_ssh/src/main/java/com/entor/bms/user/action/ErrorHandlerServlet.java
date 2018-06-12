package com.entor.bms.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErrorHandlerServlet")
public class ErrorHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErrorHandlerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer statusCode = (Integer) request.getAttribute("javax.action.error.status_code");
		Throwable throwable = (Throwable) request.getAttribute("javax.action.error.exception");

		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		if (statusCode == 404) {
			response.sendRedirect("404.html");
			return;
		}

		if (throwable != null) {
			response.sendRedirect("500.html");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
