package com.banking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;
import com.banking.exception.AccountNotFoundException;
import com.banking.exception.UserNotFoundException;
import com.banking.mapper.AccountMapper;
import com.banking.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accountDetails")
public class AccountController {
	
	private final AccountService accountService;
	private final AccountMapper accountMapper;
	
	@PostMapping("/createAccount")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) throws UserNotFoundException {
		
		Account createdAccount = accountService.createAccount(accountDTO);
		return ResponseEntity.ok(accountMapper.accountToAccountDTO(createdAccount));
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<AccountDTO>> getAccountsByUserId(@PathVariable String userId) throws AccountNotFoundException{
		List<AccountDTO> accountDTO = accountService.getAccountsByUserId(userId);
		if (accountDTO.isEmpty()) {
            //return ResponseEntity.noContent().build();
			throw new AccountNotFoundException("Account is  not avaiable for the user, Please create an Account");
        }
        return ResponseEntity.ok(accountDTO);
	}
	
	@GetMapping("/accountNumber/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccountByAccountNumber(@PathVariable String accountNumber) throws AccountNotFoundException {
	    Optional<Account> accountOpt = accountService.getAccountByAccountNumber(accountNumber);
	    if (accountOpt.isEmpty()) {
	        throw new AccountNotFoundException("Account not found with the AccountNumber, please create an account.");
	    }
	    AccountDTO accountDTO = accountMapper.accountToAccountDTO(accountOpt.get());
	    return ResponseEntity.ok(accountDTO);
	}

}
