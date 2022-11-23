package com.fooddeliveryapp.dto;
import lombok.Data;

@Data
public class OwnerRegDto {
	private Long restaurantId;
	private String restaurantName;
	private String restaurantMobile;
	private String ownerFirstName;
	private String ownerLastName;
	private String restaurantCity;
	private String restaurantState;
	private String restaurantPincode;
	private String email;
	private String password;
	private String role;
}
