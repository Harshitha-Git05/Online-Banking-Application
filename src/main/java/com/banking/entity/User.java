package com.banking.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.banking.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "user_Id")
	private String userId;
	
	@Column(name = "first_name", nullable = false, length = 25)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 25)
	private String lastName;
	
	@Column(name = "dob", nullable = false)
	private LocalDate dob;
	
	@Column(name = "email_ID", unique = true, nullable = false, length = 50)
	private String email;
	
	@Column(name = "Phone_Number", unique = true, nullable = false, length = 15)
	private String phoneNumber;
	
	@Column(name = "password", unique = true, nullable = false, length = 8)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;     // USER or ADMIN
	
	
	// to generate userId automatically
	@PrePersist
	private void generateUserId() {
		if(this.userId==null) {
			String year = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMM"));
			String randomdigits = String.format("%6d", new Random().nextInt(1_00_000));
			this.userId = year + randomdigits;
		}
	}
	
	
	
	

}
