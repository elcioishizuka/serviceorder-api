package com.github.elcioishizuka.serviceorder.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(Long id){
        super(String.format("Customer id = %s not found", id));
    }

}
