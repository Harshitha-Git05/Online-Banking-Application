package com.banking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.banking.model.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="account")
public class Account {
	
	@Id
	private String accountId;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
	@Column(name = "account_Number", nullable = false, unique = true)
	private String accountNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "account_Type", nullable = false)
	private AccountType accountType;
	
	@Column(name = "balance", nullable = false)
	private BigDecimal balance;
	
	@Column(name = "created_At", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	
	// to generate AccountID & AccountNumber automatically
	@PrePersist
	private void generateAccountDetails() {
		if(this.accountId==null) {
			String year = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMYYYY"));
			this.accountId = year + System.currentTimeMillis();
			this.accountNumber = "GHR" + accountId;
			this.createdAt = LocalDateTime.now();
	}
	

}
	
}
