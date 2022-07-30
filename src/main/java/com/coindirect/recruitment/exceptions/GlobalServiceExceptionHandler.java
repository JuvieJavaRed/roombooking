package com.coindirect.recruitment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCombinationException.class)
    public ResponseEntity<?> invalidCombinationException(InvalidCombinationException invalidCombinationException){
        log.error(invalidCombinationException.getMessage());
        return new ResponseEntity<>(invalidCombinationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnavailabilityException.class)
    public ResponseEntity<?> invalidCombinationException(UnavailabilityException unavailabilityException){
        log.error(unavailabilityException.getMessage());
        return new ResponseEntity<>(unavailabilityException.getMessage(), HttpStatus.OK);
    }
}
