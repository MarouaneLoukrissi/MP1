package com.testIC.pattern.strategy;

import com.testIC.service.BankingService;

public interface TransactionStrategy {
    void execute(BankingService service);
}