package com.github.elcioishizuka.serviceorderapi.model;

public enum ServiceOrderStatus {

    OPEN("Open"),
    CLOSED("Closed"),
    CANCELLED("Cancelled");

    private final String description;

    private ServiceOrderStatus(String description){
        this.description=description;
    }

}
