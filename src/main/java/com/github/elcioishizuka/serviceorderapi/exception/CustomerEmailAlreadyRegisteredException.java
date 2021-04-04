package com.github.elcioishizuka.serviceorderapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerEmailAlreadyRegisteredException extends Exception{

    public CustomerEmailAlreadyRegisteredException(String email){
        super(String.format("Following e-mail was already registered: %s", email));
    }
}
