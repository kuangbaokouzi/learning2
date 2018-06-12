package com.entor.bms.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "/CharacterEncodingFilter", urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("doFilter() -> 开始编码（Encoding-UTF-8）");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		LOGGER.info("doFilter() -> 结束编码（Encoding-UTF-8）");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {

	}

}
