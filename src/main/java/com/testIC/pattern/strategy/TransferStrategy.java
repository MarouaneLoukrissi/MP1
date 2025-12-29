package com.testIC.pattern.strategy;

import com.testIC.service.BankingService;

public class TransferStrategy implements TransactionStrategy {
    private final String from;
    private final String to;
    private final double amount;
    public TransferStrategy(String from, String to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
    @Override
    public void execute(BankingService service) {
        service.transfer(from, to, amount);
    }
}
