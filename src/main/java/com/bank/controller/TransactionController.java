package com.bank.controller;

import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService,TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/transfer")
    public String transaction(Model model){
        // we need all accounts to provide them as sender, receiver
        model.addAttribute("accounts",accountService.listAllAcount());
        // we need empty transaction object to get info from UI
        model.addAttribute("transaction", Transaction.builder().build());
        // we need list of last 10 transactions
        model.addAttribute("lastTransactions",transactionService.transactionsList());

        return "/transaction/make-transfer";
    }
}
