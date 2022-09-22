package com.bank.controller;

import com.bank.enums.AccountType;
import com.bank.model.Account;
import com.bank.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("accountList",accountService.listAllAcount());
        return "account/index";
    }

    @GetMapping("/create-form")
    public String createForm(Model model){
        model.addAttribute("account", Account.builder().build());
        model.addAttribute("accountType", AccountType.values());

        return "account/create-account";
    }
}
