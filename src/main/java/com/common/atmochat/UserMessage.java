package com.common.atmochat;

public class UserMessage {

    private String message;
    private String user;

    public UserMessage(){
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}