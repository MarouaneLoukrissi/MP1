package com.testIC.pattern.strategy;

import com.testIC.service.BankingService;

public class WithdrawStrategy implements TransactionStrategy {
    private final String user;
    private final double amount;
    public WithdrawStrategy(String user, double amount) {
        this.user = user;
        this.amount = amount;
    }
    @Override
    public void execute(BankingService service) {
        if(this.amount<=0) {
            System.out.println("Can't withdraw non positive amount: " + this.amount);
            return;
        }
        service.withdraw(user, amount);
    }
}