package com.entor.action;

import com.entor.to.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {

    private MessageStore messageStore;

    public MessageStore getMessageStore() {
        return messageStore;
    }

    @Override
    public String execute() throws Exception {
        messageStore = new MessageStore();
        return SUCCESS;
    }
}
