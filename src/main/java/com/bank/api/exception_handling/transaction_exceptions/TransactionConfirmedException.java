package com.bank.api.exception_handling.transaction_exceptions;

public class TransactionConfirmedException extends RuntimeException{
    public TransactionConfirmedException(String message) {
        super(message);
    }
}
