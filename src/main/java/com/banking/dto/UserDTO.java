package com.banking.dto;

import java.time.LocalDate;

import com.banking.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	
    private String userId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String email;
	private String phoneNumber;
	private Role role;  		// USER,ADMIN

}
