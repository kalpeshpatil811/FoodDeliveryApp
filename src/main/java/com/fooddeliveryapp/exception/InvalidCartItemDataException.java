package com.fooddeliveryapp.exception;

public class InvalidCartItemDataException extends Exception{
    private static final long serialVersionUID = 1L;

    public InvalidCartItemDataException(String message) {
        super(message);
    }
}
