package com.bank.controller;

import com.bank.dto.AccountDTO;
import com.bank.dto.TransactionDTO;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public TransactionController(AccountService accountService,TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/make-transfer")
    public String transaction(Model model){
        // we need all accounts to provide them as sender, receiver
        model.addAttribute("accounts",accountService.listAllAcount());
        // we need empty transaction object to get info from UI
        model.addAttribute("transaction", new TransactionDTO());
        // we need list of last 10 transactions
        model.addAttribute("lastTransactions",transactionService.transactionsList());

        return "/transaction/make-transfer";
    }

    @PostMapping("/transfer")
        public String postMakeTransfer(@Valid @ModelAttribute("transaction") TransactionDTO transactionDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("accounts",accountService.listAllAcount());
            return "transaction/make-transfer";
        }



    //I have UUID but I need to provide Account to make transfer method.
        AccountDTO sender = accountService.retrieveById(transactionDTO.getSender().getId());
        AccountDTO receiver = accountService.retrieveById(transactionDTO.getReceiver().getId());

        transactionService.makeTransfer(sender,receiver, transactionDTO.getAmount(),new Date(), transactionDTO.getMessage());

        return "redirect:/make-transfer";}


    @GetMapping("/account-transaction/{id}")
    public String accountTransaction(@PathVariable("id") Long uuid,Model model){
        model.addAttribute("transactions",transactionService.findTransactionListById(uuid));
        return "/transaction/transactions";
    }
    }
