package com.bank.api.exception_handling.user_exceptions;

public class UserIncorrectData {

    private String info;


    public UserIncorrectData() {
    }

    public UserIncorrectData(String info) {
        this.info = info;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
