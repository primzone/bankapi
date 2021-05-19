package com.bank.api.exception_handling.transaction_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TransactionIncorrectData> exceptionHandler(NoSuchTransactionException exception){
        TransactionIncorrectData data = new TransactionIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TransactionIncorrectData> exceptionHandler(Exception exception){
        TransactionIncorrectData data = new TransactionIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


}
