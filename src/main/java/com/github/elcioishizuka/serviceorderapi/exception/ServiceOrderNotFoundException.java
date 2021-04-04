package com.github.elcioishizuka.serviceorderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceOrderNotFoundException extends Exception{

    public ServiceOrderNotFoundException(Long serviceOrderId){
        super(String.format("Following Service Order ID was not found: %s", serviceOrderId));
    }
}
