package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BankSimulationAppApplication.class, args);

        //get account and transaction service beans
//        AccountService accountService = context.getBean(AccountService.class);
//        TransactionService transactionService = context.getBean(TransactionService.class);
//
//        // create 2 account sender and receiver.
//        Account sender = accountService.createNewAccount(BigDecimal.valueOf(100),new Date(), AccountType.CHECKING,1L);
//        Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.SAVING,1L);
//        accountService.listAllAcount().forEach(System.out::println);
//        transactionService.makeTransfer(sender,receiver,new BigDecimal(90),new Date(),"Transaction-1");
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//        accountService.listAllAcount().forEach(System.out::println);

    }

}
