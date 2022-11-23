package com.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import com.fooddeliveryapp.dto.LoginDto;
import com.fooddeliveryapp.dto.LoginRespDto;
import com.fooddeliveryapp.entity.Login;
import com.fooddeliveryapp.exception.EmailNotFoundException;
import com.fooddeliveryapp.exception.LoginAlreadyExistsException;
import com.fooddeliveryapp.exception.LoginNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

	public List<Login> getAllLogins();
	
	public Optional<Login> getLogin(String id) throws LoginNotFoundException;
	
	public Login addLogin(Login  login) throws LoginAlreadyExistsException;
	
	public Optional<Login> deleteLogin(String id) throws LoginNotFoundException;
	
	public Login updateLogin(String id, Login login) throws LoginNotFoundException;
	
	Login login(Login credentials);

	LoginRespDto login(LoginDto loginDto);

	LoginRespDto logout(String email) throws EmailNotFoundException;
}
