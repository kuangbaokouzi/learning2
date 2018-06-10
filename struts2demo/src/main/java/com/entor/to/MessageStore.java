package com.entor.to;

public class MessageStore {
    private String message;

    public MessageStore() {
        message = "Hello Struts UserModel";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
