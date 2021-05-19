package com.bank.api.exception_handling.card_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CardGlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<CardIncorrectData> exceptionHandler(NoSuchCardException exception){

        CardIncorrectData data = new CardIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }
}
