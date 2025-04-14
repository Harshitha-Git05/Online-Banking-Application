package com.banking.service;

import java.util.Optional;

import com.banking.dto.UserDTO;
import com.banking.entity.User;
import com.banking.exception.UserAlreadyExistsException;
import com.banking.exception.UserNotFoundException;

public interface UserService {
	
	User registerUser(User user) throws UserAlreadyExistsException;
	Optional<User> getByUserID(String id);
	User login(String identifier, String password) throws UserNotFoundException;
	
	

}
