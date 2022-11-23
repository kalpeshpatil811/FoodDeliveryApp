package com.fooddeliveryapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fooddeliveryapp.dto.LoginDto;
import com.fooddeliveryapp.dto.LoginRespDto;
import com.fooddeliveryapp.entity.Login;
import com.fooddeliveryapp.exception.EmailNotFoundException;
import com.fooddeliveryapp.exception.LoginAlreadyExistsException;
import com.fooddeliveryapp.exception.LoginInvalidCredentialsException;
import com.fooddeliveryapp.exception.LoginNotFoundException;
import com.fooddeliveryapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	

	@GetMapping("/login")
	public List<Login> getAllLogins(){
		return loginService.getAllLogins();
	}
	
	@GetMapping("/login/{email}")
	public Optional<Login> getLogin(@PathVariable String email) throws LoginNotFoundException {
		return loginService.getLogin(email);
	}
	
	@DeleteMapping("/login/delete/{email}")
	public Optional<Login> deleteLogin(@PathVariable String email) throws LoginNotFoundException{
		return loginService.deleteLogin(email);
	}

	@PutMapping("/login/update/{email}")
	public Login updateLogin(@PathVariable String email, @Valid @RequestBody Login login) throws LoginNotFoundException{
		return loginService.updateLogin(email, login);
	}
	
	@PostMapping("/login")
	ResponseEntity<Login> login(@Valid @RequestBody Login credentials) throws LoginAlreadyExistsException {
		Login login= loginService.login(credentials);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@PostMapping("/login/dto")
	ResponseEntity<LoginRespDto> login(@RequestBody LoginDto loginDto) throws LoginInvalidCredentialsException {
		LoginRespDto login= loginService.login(loginDto);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
	
	@PatchMapping("/logout/{email}")
	ResponseEntity<LoginRespDto> logout(@PathVariable("email") String email) throws EmailNotFoundException {
		LoginRespDto resp = loginService.logout(email);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
}
