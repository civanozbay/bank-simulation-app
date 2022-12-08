package com.bank.service;

import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {
    void makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message);

    List<TransactionDTO> findAllTransaction();

    List<TransactionDTO> lastTransactionsList();

    List<TransactionDTO> findTransactionListById(Long uuid);

}
