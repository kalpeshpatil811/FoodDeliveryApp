package com.fooddeliveryapp.exception;

public class DishIdNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DishIdNotFoundException(String message){
        super(message);
    }
}
