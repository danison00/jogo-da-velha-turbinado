package com.dan.jogodavelhaturbinado2.infra.exceptionHandling;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exceptions {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exception(RuntimeException e){

        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }
    
}
