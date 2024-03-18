package com.onboarding.actors.actorsws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        Exception resourceNotFoundException = new Exception(ExceptionData.reasonResourceNotFound, e.getMessage());
        return new ResponseEntity<>(resourceNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InternalServerErrorException.class })
    public ResponseEntity<Object> handleInternalServerErrorException(InternalServerErrorException e) {
        Exception internalServerErrorException = new Exception(ExceptionData.reasonInternalServerError, e.getMessage());
        return new ResponseEntity<>(internalServerErrorException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
