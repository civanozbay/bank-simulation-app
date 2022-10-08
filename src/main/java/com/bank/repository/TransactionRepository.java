package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();

    public Transaction save(Transaction transaction){
         transactionList.add(transaction);
         return transaction;
    }

    public List<Transaction> findAll() {
        return transactionList;
    }

    public List<Transaction> lastTransaction() {
        return transactionList.stream().sorted(Comparator.comparing(Transaction::getCreationDate).reversed())
                .limit(10).collect(Collectors.toList());
    }
    public List<Transaction> getTransactionListById(UUID id){
        return transactionList.stream().filter(transaction -> transaction.getSender().equals(id)
            || transaction.getReceiver().equals(id)).collect(Collectors.toList());
    }
}
