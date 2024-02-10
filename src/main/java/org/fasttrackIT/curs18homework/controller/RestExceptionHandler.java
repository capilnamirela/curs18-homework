package org.fasttrackIT.curs18homework.controller;

import org.fasttrackIT.curs18homework.exceptions.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransactionNotFoundException(TransactionNotFoundException exception) {
        return exception.getMessage();
    }
}
