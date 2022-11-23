package com.fooddeliveryapp.exception;

public class LoginInvalidCredentialsException extends RuntimeException{
	
	public LoginInvalidCredentialsException(String msg) {
		super(msg);
	}

}
