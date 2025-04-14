package com.banking.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.banking.dto.AccountDTO;
import com.banking.entity.Account;

@Component
public class AccountMapper {
	
	public Account accountDTOToAccount(AccountDTO accountDTO) {
		Account account = Account.builder()
				         .accountId(accountDTO.getAccountId()) 
				         .user(accountDTO.getUser())
				         .accountType(accountDTO.getAccountType())
				         .balance(accountDTO.getBalance())
				         .build();
		
		return account;
				 

	}
	
	
	
	public AccountDTO accountToAccountDTO(Account account) {
		AccountDTO accountDTO =  AccountDTO.builder()
				                .accountId(account.getAccountId())
				                .user(account.getUser())
				                .accountNumber(account.getAccountNumber())
				                .accountType(account.getAccountType())
		                        .balance(account.getBalance())
		                        .createdAt(account.getCreatedAt())
		                        .build();
		
		return accountDTO;
	}
	
	public AccountDTO listAccountToAccountDTO(List<Account> account) {
		AccountDTO accountDTO =  AccountDTO.builder()
				                .accountId(((AccountDTO) account).getAccountId())
				                .user(((AccountDTO) account).getUser())
				                .accountNumber(((AccountDTO) account).getAccountNumber())
				                .accountType(((AccountDTO) account).getAccountType())
		                        .balance(((AccountDTO) account).getBalance())
		                        .createdAt(((AccountDTO) account).getCreatedAt())
		                        .build();
		
		return accountDTO;
	}

}
