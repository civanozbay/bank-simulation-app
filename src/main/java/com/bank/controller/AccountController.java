package com.bank.controller;

import com.bank.dto.AccountDTO;
import com.bank.enums.AccountType;
import com.bank.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

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
        model.addAttribute("account", new AccountDTO());
        model.addAttribute("accountType", AccountType.values());

        return "account/create-account";
    }

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model){ // i need to get the information from fill out account object to the that method
        if(bindingResult.hasErrors()){
            model.addAttribute("accountType", AccountType.values());
            return "account/create-account";
        }
        accountService.createNewAccount(accountDTO.getBalance(),new Date(), accountDTO.getAccountType(), accountDTO.getUserId());

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id") UUID id){
        accountService.deleteAccount(id);
        return "redirect:/index";
    }
}
