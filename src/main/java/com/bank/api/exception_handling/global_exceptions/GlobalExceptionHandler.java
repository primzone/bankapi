package com.bank.api.exception_handling.global_exceptions;

import com.bank.api.exception_handling.transaction_exceptions.NoSuchTransactionException;
import com.bank.api.exception_handling.transaction_exceptions.TransactionIncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<Global> exceptionHandler(Exception exception){
        Global data = new Global();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
