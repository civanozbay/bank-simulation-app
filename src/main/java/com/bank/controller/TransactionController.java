package com.bank.controller;

import com.bank.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    private final AccountService accountService;

    public TransactionController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transfer")
    public String transaction(){

        return "/transaction/make-transfer";
    }
}
