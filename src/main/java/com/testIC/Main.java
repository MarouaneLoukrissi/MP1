package com.testIC;


import com.testIC.pattern.factory.AccountFactory;
import com.testIC.pattern.observer.AuditLogger;
import com.testIC.pattern.observer.NotificationService;
import com.testIC.service.BankingService;
import com.testIC.service.TransactionService;

public class Main {
    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        transactionService.addObserver(new AuditLogger());
        transactionService.addObserver(new NotificationService());
        BankingService bankingService = new BankingService(transactionService);
        bankingService.addAccount(AccountFactory.create("user-1", 3000));
        bankingService.addAccount(AccountFactory.create("user-2", 1500));
        bankingService.deposit("user-1", 600);
        bankingService.transfer("user-1", "user-2", 450);
        System.out.println("Solde user-1: " + bankingService.getBalance("user-1"));
        System.out.println("Solde user-2: " + bankingService.getBalance("user-2"));
    }
}
