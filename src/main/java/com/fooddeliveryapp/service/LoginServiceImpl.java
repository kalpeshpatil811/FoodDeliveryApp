package com.fooddeliveryapp.service;

import java.util.List;
import java.util.Optional;

import com.fooddeliveryapp.dto.LoginDto;
import com.fooddeliveryapp.dto.LoginRespDto;
import com.fooddeliveryapp.entity.Login;
import com.fooddeliveryapp.exception.EmailNotFoundException;
import com.fooddeliveryapp.exception.LoginAlreadyExistsException;
import com.fooddeliveryapp.exception.LoginInvalidCredentialsException;
import com.fooddeliveryapp.exception.LoginNotFoundException;
import com.fooddeliveryapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public List<Login> getAllLogins() {
		return loginRepository.findAll();
	}

	@Override
	public Optional<Login> getLogin(String id) throws LoginNotFoundException {
		Optional<Login> loginData = loginRepository.findById(id);
		if(!loginData.isEmpty()) {
			return loginRepository.findById(id);
		}
		else {
			
			throw new LoginNotFoundException("Login Not Found with id "+id);
		}
	}

	@Override
	public Login addLogin(Login login) throws LoginAlreadyExistsException {
		Optional<Login> loginData = loginRepository.findById(login.getEmail());
		if(loginData.isEmpty()) {
			return loginRepository.save(login);
		}
		else {
			
			throw new LoginAlreadyExistsException("Login Already Exists with id "+ login.getEmail());
		}
	}

	@Override
	public Optional<Login> deleteLogin(String id) throws LoginNotFoundException {
		Optional<Login> loginData = loginRepository.findById(id);
		if(!loginData.isEmpty()) {
			loginRepository.deleteById(id);
			return loginData;
		}
		else {
			
			throw new LoginNotFoundException("Login Not Found with id "+id);
		}
	}

	@Override
	public Login updateLogin(String id, Login login) throws LoginNotFoundException {
		Optional<Login> loginData = loginRepository.findById(id);
		if(!loginData.isEmpty()) {
			login.setEmail(id);
			return loginRepository.save(login);
		}
		else {
			
			throw new LoginNotFoundException("Login Not Found with id "+id);
		}
	}

	@Override
	public Login login(Login credentials) {
		// get login details from db
				Optional<Login> dbLoginCred = loginRepository.findById(credentials.getEmail());

				if (dbLoginCred.isPresent()) {
					// compare db password with user provided password
					// if password matching return credentials else throw exception
					Login login = dbLoginCred.get();
					if (login.getPassword().equals(credentials.getPassword())
							&& login.getRole().equals(credentials.getRole())) {
						login.setLoggedIn(true);
						return loginRepository.save(login);
						
					} else {
						throw new LoginInvalidCredentialsException("Invalid credentials!");
					}
				} else {
					// throw exception if given email not present in the db.
					throw new LoginInvalidCredentialsException("User not found with email: "+credentials.getEmail());
				}
	}

	@Override
	public LoginRespDto login(LoginDto loginDto) {
		Optional<Login> dbLoginOpt = loginRepository.findById(loginDto.getEmail());

		if (dbLoginOpt.isPresent()) {
			// compare db password with user provided password
			// if password matching return credentials else throw exception
			Login login = dbLoginOpt.get();
			if (login.getPassword().equals(loginDto.getPassword())) {
				
				// if credentials matches, set loggedIn flag as true and save
				login.setLoggedIn(true);
				Login updatedLogin = loginRepository.save(login);
				
				// convert Login to LoginRespDto Obj
				LoginRespDto resDto = new LoginRespDto();
				resDto.setEmail(login.getEmail());
				resDto.setRole(login.getRole());
				resDto.setLoggedIn(login.isLoggedIn());
				
				return resDto;
				
			} else {
				throw new LoginInvalidCredentialsException("Invalid credentials!");
			}
		} else {
			// throw exception if given email not present in the db.
			throw new LoginInvalidCredentialsException("User not found with email: "+loginDto.getEmail());
		}
	}

	@Override
	public LoginRespDto logout(String email) throws EmailNotFoundException {
		Optional<Login> dbLoginOpt = loginRepository.findById(email);
		if(dbLoginOpt.isPresent()) {
			// update isLoggedIn flag as false and save
			Login login = dbLoginOpt.get();
			
			// Update flag to false and save
			login.setLoggedIn(false);
			Login updatedLogin = loginRepository.save(login);
			
			// Convert Login obj to LoginRespDto
			LoginRespDto resDto = new LoginRespDto();
			
			resDto.setLoggedIn(false);
					
			// return LoginRespDto obj
			return resDto;
		} else {
			throw new EmailNotFoundException("User not found with email: "+email);
		}
	}
}
