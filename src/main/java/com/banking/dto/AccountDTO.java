package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.banking.entity.User;
import com.banking.model.AccountType;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {
	
    private String accountId;
	private User user;
	private String accountNumber;
	private AccountType accountType;    //SAVINGS, CURRENT 
	private BigDecimal balance;
	private LocalDateTime createdAt;
	

}
