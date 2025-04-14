package com.banking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.UserDTO;
import com.banking.entity.User;
import com.banking.exception.UserAlreadyExistsException;
import com.banking.exception.UserNotFoundException;
import com.banking.mapper.UserMapper;
import com.banking.repository.UserRepository;
import com.banking.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserMapper userMapper;

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		
		if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists!");
        }

        if (userRepo.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("User with this phone number already exists!");
        }
        
	//	User user = userMapper.userDTOtoUser(userDTO);
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getByUserID(String id) {
		return userRepo.findById(id);
	}

	@Override
	public User login(String identifier, String password) throws UserNotFoundException {
		Optional<User> userOpt = userRepo.findByEmail(identifier);
		if(userOpt.isEmpty()) {
			userOpt = userRepo.findByPhoneNumber(identifier);
		}
		
		User user = userOpt.orElseThrow(() -> 
		                    new UserNotFoundException("User not found with given phone/email"));
		
		if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid password");
        }
		
		return user;
	}

}
