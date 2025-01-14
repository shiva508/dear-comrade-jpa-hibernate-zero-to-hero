package com.comrade.exception;

import com.comrade.model.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NewsException.class})
    public ResponseEntity<CommonResponse> newsExceptionHandler(NewsException exception){
        CommonResponse commonResponse = CommonResponse.builder()
                                             .message(exception.getMessage())
                                             .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                             .timestamp(new Date()).build();
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OpinionException.class})
    public ResponseEntity<CommonResponse> opinionExceptionHandler(OpinionException exception){
        CommonResponse commonResponse = CommonResponse.builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date()).build();
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
}
