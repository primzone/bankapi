package com.bank.api.exception_handling.card_exceptions;

public class CardNotConfirmedException extends RuntimeException{
    public CardNotConfirmedException(String message) {
        super(message);
    }
}
