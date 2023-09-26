package com.example.Swigato.exception;

public class MenuItemNotFoundException extends RuntimeException{

    public MenuItemNotFoundException(String message) {
        super(message);
    }
}
