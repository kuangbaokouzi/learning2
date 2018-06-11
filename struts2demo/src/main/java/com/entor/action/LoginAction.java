package com.entor.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAction extends ActionSupport {

    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    private String loginName;
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {

        LOGGER.info("loginName:{}", loginName);
        LOGGER.info("password:{}", password);

        return "logon";
    }
}
