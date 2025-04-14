package com.banking.service;

import java.util.List;

import com.banking.dto.TransactionDTO;
import com.banking.entity.Transaction;
import com.banking.exception.AccountNotFoundException;
import com.banking.model.TransactionType;

public interface TransactionService {
	
	TransactionDTO performTransaction(TransactionDTO transactionDTO) throws AccountNotFoundException;
    List<Transaction> getTransactionsByAccountId(String accountId);
    List<Transaction> getTransactionsByAccountNumber(String accountNumber);
    List<Transaction> getTransactionsByType(TransactionType transactionType);

}
