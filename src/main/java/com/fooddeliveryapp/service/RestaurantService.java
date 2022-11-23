package com.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fooddeliveryapp.dto.OwnerRegDto;
import com.fooddeliveryapp.dto.OwnerRegRespDto;
import com.fooddeliveryapp.entity.RestaurantOwner;
import com.fooddeliveryapp.exception.OwnerNotFoundException;
import com.fooddeliveryapp.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

	RestaurantOwner addRestaurant(@Valid RestaurantOwner restaurantOwner);

	OwnerRegRespDto regOwner(@Valid OwnerRegDto owner) throws UserAlreadyExistsException;

	RestaurantOwner updateOwner(Long id, RestaurantOwner owner) throws OwnerNotFoundException;

	Optional<RestaurantOwner> deleteOwner(Long id) throws OwnerNotFoundException;

	Optional<RestaurantOwner> getowner(Long id) throws OwnerNotFoundException;

	List<RestaurantOwner> getAllRestaurants();

	Optional<RestaurantOwner> getRestaurant(Long id) throws OwnerNotFoundException;

}
