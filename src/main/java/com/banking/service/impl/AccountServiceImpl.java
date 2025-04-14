package com.banking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.UserNotFoundException;
import com.banking.mapper.AccountMapper;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import com.banking.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	
	private final AccountRepository accountRepo;
	private final AccountMapper accountMapper;
	private final UserRepository userRepo;
	
	@Override
	public Account createAccount(AccountDTO accountDTO) throws UserNotFoundException {
		
        String userId = accountDTO.getUser().getUserId();

        // Step 2: Check if user exists
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found. Please register first.");
        }

        // Step 3: Set the full user object from DB
        accountDTO.setUser(userOptional.get());

        Account account = accountMapper.accountDTOToAccount(accountDTO);
        accountRepo.save(account);
        
        return  accountRepo.save(account);
         
         
	}

	@Override
	public List<AccountDTO> getAccountsByUserId(String userId) throws AccountNotFoundException{
		return accountRepo.findByUser_UserId(userId);
	}

	@Override
	public Optional<Account> getAccountByAccountNumber(String accountNumber) {
	    return accountRepo.findByAccountNumber(accountNumber);
	}


}
