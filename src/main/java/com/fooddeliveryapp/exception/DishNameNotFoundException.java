package com.fooddeliveryapp.exception;

public class DishNameNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public DishNameNotFoundException(String message) {
        super(message);
    }
}
