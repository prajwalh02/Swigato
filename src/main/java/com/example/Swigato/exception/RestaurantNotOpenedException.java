package com.example.Swigato.exception;

public class RestaurantNotOpenedException extends RuntimeException{

    public RestaurantNotOpenedException(String message) {
        super(message);
    }
}
