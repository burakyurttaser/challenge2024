package com.lydiasystems.challenge.fault;


import com.lydiasystems.challenge.fault.model.ErrorRestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorRestResponse> handleAppException(AppException e) {
        final ErrorRestResponse errorRestResponse = new ErrorRestResponse(e.getCode(), e.getMessage(), new Date());
        return new ResponseEntity<>(errorRestResponse, HttpStatus.BAD_REQUEST);
    }
}