package com.banking.service;

import java.util.List;
import java.util.Optional;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.UserNotFoundException;

public interface AccountService {
	
	Account createAccount(AccountDTO accountDTO) throws UserNotFoundException;
	List<AccountDTO> getAccountsByUserId(String userId) throws AccountNotFoundException;
    Optional<Account> getAccountByAccountNumber(String accountNumber);


}
