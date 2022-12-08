package com.bank.service.impl;

import com.bank.dto.AccountDTO;
import com.bank.entity.Account;
import com.bank.enums.AccountStatus;
import com.bank.enums.AccountType;
import com.bank.mapper.MapperUtil;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    private final MapperUtil mapperUtil;

    public AccountServiceImpl(MapperUtil mapperUtil,AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.mapperUtil=mapperUtil;
    }

    @Override
    public void createNewAccount(AccountDTO accountDTO) {
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setCreationDate(new Date());
        accountRepository.save(mapperUtil.convert(accountDTO,new Account()));
    }

    @Override
    public List<AccountDTO> listAllAcount() {
        return accountRepository.findAll().stream().
                map(account -> mapperUtil.convert(account,new AccountDTO())).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account byId = accountRepository.findById(id).get();
        byId.setAccountStatus(AccountStatus.DELETED);
        accountRepository.save(byId);
    }

    @Override
    public AccountDTO retrieveById(Long id) {
        Optional<Account> byId = accountRepository.findById(id);
        return mapperUtil.convert(byId, new AccountDTO());
    }

    @Override
    public List<AccountDTO> listAllActiveAccounts() {
        List<Account> byAccountStatus_active = accountRepository.findByAccountStatus(AccountStatus.ACTIVE);

        return byAccountStatus_active.stream().
                map(account -> mapperUtil.convert(account,new AccountDTO())).
                collect(Collectors.toList());
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        accountRepository.save(mapperUtil.convert(accountDTO,new Account()));
    }
}
