package com.entor.action;

import com.entor.model.UserModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistAction extends ActionSupport implements ModelDriven<UserModel> {

    private static final Logger LOGGER = LogManager.getLogger(RegistAction.class);

    private UserModel userModel;

    @Override
    public UserModel getModel() {
        if (userModel == null)
            userModel = new UserModel();
        return userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public String regist() {

        LOGGER.info("loginName:{}", userModel.getLoginName());
        LOGGER.info("password:{}", userModel.getPassword());

        return "regist";
    }
}
