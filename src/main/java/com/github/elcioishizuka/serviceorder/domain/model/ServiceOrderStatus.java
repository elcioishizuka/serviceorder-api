package com.github.elcioishizuka.serviceorder.domain.model;

public enum ServiceOrderStatus {

    OPEN("Open"),
    CLOSED("Closed"),
    CANCELLED("Cancelled");

    private final String description;

    private ServiceOrderStatus(String description){
        this.description=description;
    }

}
