package com.banking.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entity.User;
import com.banking.exception.UserAlreadyExistsException;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserId(String userId) throws UserAlreadyExistsException;
	Optional<User> findByEmail(String email);
	Optional<User> findByPhoneNumber(String phoneNumber);
}
