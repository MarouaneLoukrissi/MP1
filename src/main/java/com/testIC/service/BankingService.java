package com.testIC.service;

import com.testIC.model.Account;
import com.testIC.model.Transaction;

import java.util.HashMap;
import java.util.Map;

public class BankingService {
    private final Map<String, Account> accounts = new HashMap<>();
    private final TransactionService transactionService;
    public BankingService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    public void addAccount(Account account) {
        accounts.put(account.getOwner(), account);
    }
    public void deposit(String user, double amount) {
        Account acc = accounts.get(user);
        acc.credit(amount);
        transactionService.notify(new Transaction("Dépôt +" + amount + " pour " + user));
    }
    public void withdraw(String user, double amount) {
        Account acc = accounts.get(user);
        if (acc.getBalance() >= amount) {
            acc.debit(amount);
            transactionService.notify(new Transaction("Retrait -" + amount + " pour " + user));
        }
    }
    public void transfer(String from, String to, double amount) {
        if(amount>accounts.get(from).getBalance()){
            System.out.println("solde insuffisant pour le transfert de " + amount + " de " + from + " vers " + to);
            return;
        }
        withdraw(from, amount);
        deposit(to, amount);
        transactionService.notify(new Transaction("Transfert " + amount + " de " + from + " vers " + to));
    }
    private Account requireAccount(String user) {
        Account acc = accounts.get(user);
        if (acc == null) {
            throw new IllegalArgumentException("Unknown account/user: " + user);
        }
        return acc;
    }
    public double getBalance(String user) {
        return requireAccount(user).getBalance();
    }
}
