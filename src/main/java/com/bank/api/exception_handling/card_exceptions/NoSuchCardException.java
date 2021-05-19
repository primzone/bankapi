package com.bank.api.exception_handling.card_exceptions;

public class NoSuchCardException extends RuntimeException{
    public NoSuchCardException(String message) {
        super(message);
    }
}
