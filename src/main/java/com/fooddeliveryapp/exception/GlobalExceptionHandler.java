package com.fooddeliveryapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Cart Global Exceptions

    @ExceptionHandler(value = CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CartItemDoesNotExistsException.class)
    public ResponseEntity<Object> handleCartItemDoesNotExistsException(CartItemDoesNotExistsException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidCartItemDataException.class)
    public ResponseEntity<Object> handleInvalidCartItemDataException(InvalidCartItemDataException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    // Dish Global Exceptions

    @ExceptionHandler(value= DishIdNotFoundException.class)
    public ResponseEntity<Object> handleDishIdNotFoundException(DishIdNotFoundException exception){
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= InvalidDishDataException.class)
    public ResponseEntity<Object> handleInvalidDishDataException(InvalidDishDataException exception){
        return new ResponseEntity<Object>(exception.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = DishNameNotFoundException.class)
    public ResponseEntity<Object> handleDishNameNotFoundException(DishNameNotFoundException exception){
        return new ResponseEntity<Object>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidDishDetailsException.class)
    public ResponseEntity<Object> handleInvalidDishDetailsException(InvalidDishDetailsException exception){
        return new ResponseEntity<Object>(exception.getMessage(),HttpStatus.CONFLICT);
    }

}