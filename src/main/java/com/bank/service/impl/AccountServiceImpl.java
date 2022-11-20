package com.bank.service.impl;

import com.bank.dto.AccountDTO;
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
    public AccountDTO createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        AccountDTO accountDTO = new AccountDTO();
        return accountRepository.save(accountDTO);
    }

    @Override
    public List<AccountDTO> listAllAcount() {
        return accountRepository.findAll().stream().
                map(account -> mapperUtil.convert(account,new AccountDTO())).
                collect(Collectors.toList());
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
