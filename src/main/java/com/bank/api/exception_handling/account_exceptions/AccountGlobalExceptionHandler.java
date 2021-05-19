package com.bank.api.exception_handling.account_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AccountIncorrectData> exceptionHandler(NoSuchAccountException exception){

        AccountIncorrectData data = new AccountIncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
