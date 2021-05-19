package com.bank.api.exception_handling.account_exceptions;

public class NoSuchAccountException extends RuntimeException{
    public NoSuchAccountException(String message) {
        super(message);
    }
}
