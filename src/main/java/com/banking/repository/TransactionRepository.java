package com.banking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.entity.Transaction;
import com.banking.model.TransactionType;

public interface TransactionRepository extends  JpaRepository<Transaction, UUID>{
	
	List<Transaction> findByAccount_AccountId(String accountID);
	List<Transaction> findByAccount_AccountNumber(String accountNumber);
	List<Transaction> findByTransactionType(TransactionType transactionType);

}
