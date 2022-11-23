package com.fooddeliveryapp.exception;

public class CartItemDoesNotExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public CartItemDoesNotExistsException(String message) {
        super(message);
    }
}
