package com.bank.repository;

import com.bank.dto.AccountDTO;
import com.bank.exception.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {

    public static List<AccountDTO> accountDTOList = new ArrayList<>();

    public AccountDTO save(AccountDTO accountDTO){
        accountDTOList.add(accountDTO);
        return accountDTO;
    }

    public List<AccountDTO> findAll() {
        return accountDTOList;
    }

    public AccountDTO findBy(Long id) {
        // write a method, that finds the account inside the list, if not throws
        // RecordNotFoundException
        return accountDTOList.stream().filter(account -> account.getId().equals(id))
                .findAny().orElseThrow(() -> new RecordNotFoundException("account not exist in database"));
    }

}
