package com.bank.api.exception_handling.transaction_exceptions;

public class NoSuchTransactionException extends RuntimeException{

    public NoSuchTransactionException(String message) {
        super(message);
    }
}
