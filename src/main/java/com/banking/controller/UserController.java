package com.banking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.LoginRequest;
import com.banking.dto.UserDTO;
import com.banking.entity.User;
import com.banking.exception.UserAlreadyExistsException;
import com.banking.mapper.UserMapper;
import com.banking.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	
	@PostConstruct
	public void init() {
	    System.out.println("✅ UserController initialized successfully!");
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws UserAlreadyExistsException{
		User registeredUser = userService.registerUser(user);
		return ResponseEntity.ok(userMapper.userToUserDTO(registeredUser));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		Optional<User> userOpt = userService.getByUserID(id);
		 return userOpt.map(user -> ResponseEntity.ok(userMapper.userToUserDTO(user)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			String identifier = loginRequest.getIdentifier();
		    String password = loginRequest.getPassword();
		    User userOptional = userService.login(identifier, password);
		    return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}

}
