package com.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import com.fooddeliveryapp.dto.OwnerRegDto;
import com.fooddeliveryapp.dto.OwnerRegRespDto;
import com.fooddeliveryapp.entity.Login;
import com.fooddeliveryapp.entity.RestaurantOwner;
import com.fooddeliveryapp.exception.OwnerNotFoundException;
import com.fooddeliveryapp.exception.UserAlreadyExistsException;
import com.fooddeliveryapp.repository.LoginRepository;
import com.fooddeliveryapp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	LoginRepository loginRepository;

	@Override
	public List<RestaurantOwner> getAllRestaurants() {
		return restaurantRepository.findAll();
	}
	
	@Override
	public Optional<RestaurantOwner> getRestaurant(Long id) throws OwnerNotFoundException {
		
		Optional<RestaurantOwner> ownerData = restaurantRepository.findById(id);
		if(!ownerData.isEmpty()) {
			return restaurantRepository.findById(id);
		}
		else {
			throw new OwnerNotFoundException("Restuarant Not Found with id "+id);
		}
	}

	@Override
	public RestaurantOwner addRestaurant(RestaurantOwner restaurantOwner) {

		Optional<RestaurantOwner> restaurantData = restaurantRepository.findById(restaurantOwner.getRestaurantId());
		if (restaurantData.isEmpty()) {
			return restaurantRepository.save(restaurantOwner);
		}
		return restaurantOwner;
	}

	@Override
	public RestaurantOwner updateOwner(Long id, RestaurantOwner owner) throws OwnerNotFoundException {
		Optional<RestaurantOwner> ownerData = restaurantRepository.findById(id);
		if (!ownerData.isEmpty()) {
			owner.setRestaurantId(id);
			return restaurantRepository.save(owner);
		} else {
			throw new OwnerNotFoundException("Restaurant Owner Not Found with id " + id);
		}
	}

	@Override
	public Optional<RestaurantOwner> deleteOwner(Long id) throws OwnerNotFoundException {
		Optional<RestaurantOwner> ownerData = restaurantRepository.findById(id);
		if (!ownerData.isEmpty()) {
			restaurantRepository.deleteById(id);
			return ownerData;
		} else {
			throw new OwnerNotFoundException("Restaurant Not Found with id " + id);
		}
	}

	@Override
	public Optional<RestaurantOwner> getowner(Long id) throws OwnerNotFoundException {
		Optional<RestaurantOwner> ownerData = restaurantRepository.findById(id);
		if (!ownerData.isEmpty()) {
			return restaurantRepository.findById(id);
		} else {
			throw new OwnerNotFoundException("Restuarant Not Found with id " + id);
		}
	}

	@Override
	public OwnerRegRespDto regOwner(OwnerRegDto regDto) throws UserAlreadyExistsException {
		Optional<Login> loginOpt = loginRepository.findById(regDto.getEmail());
		if (loginOpt.isPresent()) {
			throw new UserAlreadyExistsException(
					"Given email address " + regDto.getEmail() + " present already! Choose different one");
		}

		RestaurantOwner restaurantOwner = new RestaurantOwner();

		restaurantOwner.setRestaurantId(regDto.getRestaurantId());
		restaurantOwner.setRestaurantName(regDto.getRestaurantName());
		restaurantOwner.setOwnerFirstName(regDto.getOwnerFirstName());
		restaurantOwner.setOwnerLastName(regDto.getOwnerLastName());
		restaurantOwner.setRestaurantMobile(regDto.getRestaurantMobile());
		restaurantOwner.setRestaurantCity(regDto.getRestaurantCity());
		restaurantOwner.setRestaurantState(regDto.getRestaurantState());
		restaurantOwner.setRestaurantPincode(regDto.getRestaurantPincode());

		Login login = new Login();
		login.setEmail(regDto.getEmail());
		login.setPassword(regDto.getPassword());
		login.setRole(regDto.getRole());
		login.setLoggedIn(false);

		restaurantOwner.setLogin(login);

		RestaurantOwner newOwner = restaurantRepository.save(restaurantOwner);

		OwnerRegRespDto resDto = new OwnerRegRespDto();
		resDto.setRestaurantId(newOwner.getRestaurantId());
		resDto.setRestaurantName(newOwner.getRestaurantName());
		resDto.setOwnerFirstName(newOwner.getOwnerFirstName());
		resDto.setOwnerLastName(newOwner.getOwnerLastName());
		resDto.setRestaurantMobile(newOwner.getRestaurantMobile());
		resDto.setEmail(newOwner.getLogin().getEmail());
		resDto.setRole(newOwner.getLogin().getRole());
		resDto.setLoggedIn(newOwner.getLogin().isLoggedIn());
		resDto.setRestaurantCity(newOwner.getRestaurantCity());
		resDto.setRestaurantState(newOwner.getRestaurantState());
		resDto.setRestaurantPincode(newOwner.getRestaurantPincode());
		return resDto;
	}
}