package com.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class LoginRespDto {
	private String email;
	private String role;
	private boolean isLoggedIn;
}