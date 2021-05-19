package com.bank.api.exception_handling.account_exceptions;

public class AccountIncorrectData {
    private String info;

    public AccountIncorrectData() {
    }

    public AccountIncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
