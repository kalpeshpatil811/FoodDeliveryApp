package com.fooddeliveryapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="login")
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	@Id
	@Email(message="email is not valid")
	@NotNull(message = "email is mandatory")
	private String email;
	
	@Size(min = 5, max = 32,message="password length should be with 5 to 32 range")
	@NotNull(message = "password is mandatory")
	private String password;

	@NotNull(message = "role is mandatory")
	private String role;
	
	private boolean isLoggedIn = false;

	public Login(String string, String string2, String string3) {
		
	}
}