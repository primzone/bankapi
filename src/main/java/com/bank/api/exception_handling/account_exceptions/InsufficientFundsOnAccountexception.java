package com.bank.api.exception_handling.account_exceptions;

public class InsufficientFundsOnAccountexception extends RuntimeException{

    public InsufficientFundsOnAccountexception(String message) {
        super(message);
    }
}
