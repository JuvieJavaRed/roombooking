package com.coindirect.recruitment.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnavailabilityException extends RuntimeException{
    private String message;
    /**
     *parameterized constructor
     *@param message exception message
     */
    public UnavailabilityException(String message){
        super(message);
        log.error(message);
    }
}
