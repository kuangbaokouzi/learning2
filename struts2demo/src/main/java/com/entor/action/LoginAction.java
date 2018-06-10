package com.entor.action;

import com.entor.to.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
    private String loginName;
    private String password;
    private MessageStore messageStore;

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


    public MessageStore getMessageStore() {
        return messageStore;
    }

    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    public String login() {
        if ("admin".equals(loginName) && "admin".equals(password)) {
            messageStore = new MessageStore();
            messageStore.setMessage("验证通过！");
        } else {
            messageStore = new MessageStore();
            messageStore.setMessage("用户名或密码错误！");
        }
        return SUCCESS;
    }
}
