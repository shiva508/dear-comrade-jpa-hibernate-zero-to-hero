package com.comrade.config.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentpoolExceptionHandler {

    @ExceptionHandler(value = ResponseNotFound.class)
    public ResponseEntity<CommonExceptonResponse> dataNotFound(ResponseNotFound responseNotFound) {
        CommonExceptonResponse commonExceptonResponse = new CommonExceptonResponse(responseNotFound.getMessage(),
                new Date());
        return new ResponseEntity<CommonExceptonResponse>(commonExceptonResponse, HttpStatus.NOT_FOUND);
    }
}
