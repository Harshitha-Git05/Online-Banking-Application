package com.banking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.TransactionDTO;
import com.banking.entity.Transaction;
import com.banking.exception.AccountNotFoundException;
import com.banking.mapper.TransactionMapper;
import com.banking.model.TransactionType;
import com.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
	
	private final TransactionService transactionService;
	private final TransactionMapper transactionMapper;
	
	 @PostMapping("/transfer")
	    public ResponseEntity<TransactionDTO> performTransaction(@RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
	        TransactionDTO result = transactionService.performTransaction(transactionDTO);
	        return ResponseEntity.ok(result);
	    }
	 
	 @GetMapping("/accountId/{accountId}")
	    public ResponseEntity<List<Transaction>> getByAccountId(@PathVariable String accountId) {
	        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
	    }
	 
	 @GetMapping("/accountNumber/{accountNumber}")
	    public ResponseEntity<List<Transaction>> getByAccountNumber(@PathVariable String accountNumber) {
	        return ResponseEntity.ok(transactionService.getTransactionsByAccountNumber(accountNumber));
	    }
	 
	 @GetMapping("/type/{transactionType}")
	    public ResponseEntity<List<Transaction>> getByTransactionType(@PathVariable TransactionType transactionType) {
	        return ResponseEntity.ok(transactionService.getTransactionsByType(transactionType));
	    }

}
