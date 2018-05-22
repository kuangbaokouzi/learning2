package com.entor.hrm.intercepter;

import com.entor.hrm.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.entor.hrm.uitl.common.HrmConstants.LOGIN;
import static com.entor.hrm.uitl.common.HrmConstants.USER_SESSION;

public class AuthorizedInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {};

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String servletPath = request.getServletPath();
        for (String uri : IGNORE_URI) {
            if (servletPath.contains(uri)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            User user = (User) request.getSession().getAttribute(USER_SESSION);
            if (user != null) {
                request.setAttribute("message", "Please logon before inviting!");
                request.getRequestDispatcher(LOGIN).forward(request, response);
                return flag;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
