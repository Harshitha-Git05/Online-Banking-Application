package com.banking.mapper;

import org.springframework.stereotype.Component;

import com.banking.dto.TransactionDTO;
import com.banking.entity.Transaction;

@Component
public class TransactionMapper {
	
	public Transaction TransactionDTOToTransaction(TransactionDTO transactionDTO) {
		Transaction transaction = Transaction.builder()
								  .transactionId(transactionDTO.getTransactionId())
								  .account(transactionDTO.getAccount())
								  .transactionType(transactionDTO.getTransactionType())
								  .amount(transactionDTO.getAmount())
								  .description(transactionDTO.getDescription())
								  .build();
		return transaction;
	}
	
	public TransactionDTO TransactionToTransactionDTO(Transaction transaction) {
		TransactionDTO transactionDTO = TransactionDTO.builder()
								  .transactionId(transaction.getTransactionId())
								  .account(transaction.getAccount())
								  .transactionType(transaction.getTransactionType())
								  .amount(transaction.getAmount())
								  .timestamp(transaction.getTimestamp())
								  .description(transaction.getDescription())
								  .build();
		return transactionDTO;
	}

}
