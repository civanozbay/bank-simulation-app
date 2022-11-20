package com.bank.service.impl;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        AccountDTO accountDTO = new AccountDTO();
        return accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> listAllAcount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(Long id) {
        AccountDTO accountDTO = accountRepository.findBy(id);
        accountDTO.setAccountStatus(AccountStatus.DELETED);
    }

    @Override
    public AccountDTO retrieveById(Long id) {
        return accountRepository.findBy(id);
    }
}
