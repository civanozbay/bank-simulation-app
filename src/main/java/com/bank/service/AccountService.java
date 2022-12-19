package com.bank.service;

import com.bank.dto.AccountDTO;
import com.bank.entity.Account;
import com.bank.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {


    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    void deleteAccount(Long id);

    AccountDTO retrieveById(Long id);

    List<AccountDTO> listAllActiveAccounts();

    void updateAccount(AccountDTO accountDTO);
}
