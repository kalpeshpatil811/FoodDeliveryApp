package com.fooddeliveryapp.exception;

public class InvalidDishDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidDishDataException(String message){
        super(message);
    }
}
