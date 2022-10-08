package com.bank.service.impl;

import com.bank.enums.AccountType;
import com.bank.exception.AccountOwnershipException;
import com.bank.exception.BadRequestException;
import com.bank.exception.BalanceNotSufficientException;
import com.bank.exception.UnderConstructionException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {
    @Value("${under_construction}")
    private boolean underConstruction;
    AccountRepository accountRepository;
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {
        if (!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnership(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
                /*
        after all validations are completed, and money is transferred, we need to create Transaction object and save/return it
         */
            //please create needed classes/ methods for this step, save the transactions.
            Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId())
                    .receiver(receiver.getId()).creationDate(creationDate).message(message).build();
            return transactionRepository.save(transaction);
        }else {
            throw new UnderConstructionException("App is under construction, try again later");
        }
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(checkSenderBalance(sender,amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }
    }
    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        // verify that sender has enough balance
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0;
    }
    private void checkAccountOwnership(Account sender, Account receiver) {
        /*
         write an if statement that checks if one of the account is saving,
         and user if of sender or receiver is not the same, throw AccountOwnershipException
         */

        if((sender.getAccountType().equals(AccountType.SAVING)
                ||receiver.getAccountType().equals(AccountType.SAVING))&& !sender.getUserId().equals(receiver.getUserId()))
        {
            throw new AccountOwnershipException("If one of the account is saving, userId must be the same");
        }
    }

    private void validateAccount(Account sender, Account receiver) {
        /*
        if any of the account is null
        if account ids are the same (same account)
        if the account exist in the database(repository)
         */

        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender accounts needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());
        
    }

    private void findAccountById(UUID id) {
        accountRepository.findBy(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> transactionsList() {
        // we want to list latest 10 transaction
        return transactionRepository.lastTransaction();

    }

    @Override
    public List<Transaction> findTransactionListById(UUID uuid) {
        return transactionRepository.getTransactionListById(uuid);
    }
}
