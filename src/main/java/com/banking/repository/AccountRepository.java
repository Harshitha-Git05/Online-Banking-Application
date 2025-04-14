package com.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	List<AccountDTO> findByUser_UserId(String userId);
	Optional<Account> findByAccountNumber(String accountNumber);
}
