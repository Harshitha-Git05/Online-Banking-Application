package com.banking.mapper;

import org.springframework.stereotype.Component;

import com.banking.dto.UserDTO;
import com.banking.entity.User;

@Component
public class UserMapper {
	
	public User userDTOtoUser(UserDTO userDTO) {
		User user = User.builder()
		           .firstName(userDTO.getFirstName())
		           .lastName(userDTO.getLastName())
		           .dob(userDTO.getDob())
		           .email(userDTO.getEmail())
		           .phoneNumber(userDTO.getPhoneNumber())
		           .role(userDTO.getRole())
		           .build();
		return user;
	}
	
	public UserDTO userToUserDTO(User user) {
		UserDTO userDTO = UserDTO.builder()
				          .userId(user.getUserId())
				          .firstName(user.getFirstName())
				          .lastName(user.getLastName())
				          .dob(user.getDob())
				          .email(user.getEmail())
				          .phoneNumber(user.getPhoneNumber())
				          .role(user.getRole())
				          .build();
		return userDTO;
	}

}
