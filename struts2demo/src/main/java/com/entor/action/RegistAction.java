package com.entor.action;

import com.entor.model.UserModel;
import com.entor.to.MessageStore;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistAction extends ActionSupport implements ModelDriven<UserModel> {

    private static final Logger LOGGER = LogManager.getLogger(RegistAction.class);

    private UserModel userModel;
    private MessageStore messageStore;

    @Override
    public UserModel getModel() {
        if (userModel == null)
            userModel = new UserModel();
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }

    public String regist() {
        LOGGER.info(" {}:{}", userModel.getLoginName(), userModel.getPassword());
        messageStore = new MessageStore();
        messageStore.setMessage("注册成功！");
        return SUCCESS;
    }
}
