package com.banking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.banking.entity.Account;
import com.banking.model.TransactionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TransactionDTO {

    private UUID transactionId;
    private Account account;
    private TransactionType transactionType; // DEPOSIT or WITHDRAWAL
    private BigDecimal amount;
    private LocalDateTime timestamp;
    private String description;

}
