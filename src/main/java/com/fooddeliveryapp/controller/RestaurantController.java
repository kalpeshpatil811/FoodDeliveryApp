package com.fooddeliveryapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fooddeliveryapp.dto.OwnerRegDto;
import com.fooddeliveryapp.dto.OwnerRegRespDto;
import com.fooddeliveryapp.entity.RestaurantOwner;
import com.fooddeliveryapp.exception.OwnerNotFoundException;
import com.fooddeliveryapp.exception.UserAlreadyExistsException;
import com.fooddeliveryapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	// Add Owner
	@PostMapping("/owner/add")
	ResponseEntity<RestaurantOwner> addRestaurant(@Valid @RequestBody RestaurantOwner restaurantOwner) {
		RestaurantOwner newOwner = restaurantService.addRestaurant(restaurantOwner);
		return new ResponseEntity<>(newOwner, HttpStatus.CREATED);
	}

	// Register owner
	@PostMapping("/owner/register")
	ResponseEntity<OwnerRegRespDto> regOwner(@Valid @RequestBody OwnerRegDto owner) throws UserAlreadyExistsException {
		OwnerRegRespDto newOwner = restaurantService.regOwner(owner);
		return new ResponseEntity<>(newOwner, HttpStatus.CREATED);
	}

	// Update Owner
	@PutMapping("/owner/update/{id}")
	ResponseEntity<RestaurantOwner> updateOwner(@PathVariable Long id, @Valid @RequestBody RestaurantOwner owner)
			throws OwnerNotFoundException {
		RestaurantOwner newOwner = restaurantService.updateOwner(id, owner);
		return new ResponseEntity<>(newOwner, HttpStatus.OK);
	}

	// Delete Owner
	@DeleteMapping("/owner/delete/{id}")
	ResponseEntity<Optional<RestaurantOwner>> deleteOwner(@PathVariable Long id) throws OwnerNotFoundException {
		Optional<RestaurantOwner> newOwner = restaurantService.deleteOwner(id);
		return new ResponseEntity<>(newOwner, HttpStatus.OK);
	}

	//Get All Restaurants
	@GetMapping("/owner/restaurants")
	public List<RestaurantOwner> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	// Get Restaurant by Restaurant ID 
	@GetMapping("/owner/restaurant/{id}")
	public Optional<RestaurantOwner> getRestaurant(@PathVariable Long id) throws OwnerNotFoundException{
		return restaurantService.getRestaurant(id);
	}
}
