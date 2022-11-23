package com.fooddeliveryapp.exception;

public class InvalidDishDetailsException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidDishDetailsException(String message){
        super(message);
    }
}
