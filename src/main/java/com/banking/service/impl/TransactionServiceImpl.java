package com.banking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.dto.TransactionDTO;
import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.exception.AccountNotFoundException;
import com.banking.mapper.TransactionMapper;
import com.banking.model.TransactionType;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.EmailService;
import com.banking.service.NotificationService;
import com.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepo;
	private final  TransactionMapper transactionMapper;
	private final AccountRepository accountRepo;
	private final NotificationService notificationService;
	private final EmailService emailService;
	
	@Override
	public TransactionDTO performTransaction(TransactionDTO transactionDTO) throws AccountNotFoundException {
		Account account = accountRepo.findByAccountNumber(transactionDTO.getAccount().getAccountNumber())
		 .orElseThrow(() -> new AccountNotFoundException("Account not found with number: " + transactionDTO.getAccount().getAccountNumber()));
		
		BigDecimal amount = transactionDTO.getAmount();
        TransactionType type = transactionDTO.getTransactionType();
        
        if (type == TransactionType.WITHDRAWL && account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance for withdrawal");
        }
        
       BigDecimal updatedBalance = type == TransactionType.DEPOSIT ? account.getBalance().add(amount) : account.getBalance().subtract(amount);
       
       account.setBalance(updatedBalance);
       accountRepo.save(account);
       
       Transaction transaction = new Transaction();
       transaction.setAccount(account);
       transaction.setAmount(amount);
       transaction.setTransactionType(type);
       transaction.setTimestamp(LocalDateTime.now());
       transaction.setDescription(transactionDTO.getDescription());

       transactionRepo.save(transaction);
       
       //Build the message
       String message = "₹" + amount + " has been " + transactionDTO.getTransactionType().name().toLowerCase() +
               " in your account ending with " + account.getAccountNumber().substring(account.getAccountNumber().length() - 4) +
               " on " + LocalDateTime.now().toString();
       
       //save the notification
       notificationService.createNotification(account.getUser().getUserId(), message);
       
    // Send Email
       emailService.sendEmail(
           account.getUser().getEmail(), // recipient
           "Transaction Alert",          // subject
           message                       // body
       );
       
      TransactionDTO trnDTO = transactionMapper.TransactionToTransactionDTO(transaction);
      
       return trnDTO;
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(String accountId) {
		return transactionRepo.findByAccount_AccountId(accountId);
	}

	@Override
	public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
		return transactionRepo.findByAccount_AccountNumber(accountNumber);
	}

	@Override
	public List<Transaction> getTransactionsByType(TransactionType transactionType) {
		return transactionRepo.findByTransactionType(transactionType);
	}

}
