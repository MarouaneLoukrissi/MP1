package com.testIC.pattern.strategy;

import com.testIC.service.BankingService;

public class DepositStrategy implements TransactionStrategy {
    private final String user;
    private final double amount;
    public DepositStrategy(String user, double amount) {
        this.user = user;
        this.amount = amount;
    }
    @Override
    public void execute(BankingService service) {
        if(this.amount <= 0) {
            System.out.println("Cannot deposit non-positive amount: " + this.amount);
            return;
        }
        service.deposit(user, amount);
    }
}