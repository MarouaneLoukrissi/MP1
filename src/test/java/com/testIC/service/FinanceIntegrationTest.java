package com.testIC.service;


import com.testIC.pattern.factory.AccountFactory;
import com.testIC.pattern.observer.AuditLogger;
import com.testIC.pattern.observer.NotificationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinanceIntegrationTest {

    @Test
    void fullBankingScenarioShouldWorkCorrectly() {
        TransactionService ts = new TransactionService();
        ts.addObserver(new AuditLogger());
        ts.addObserver(new NotificationService());
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 3000));
        bs.addAccount(AccountFactory.create("user-2", 1500));
        bs.deposit("user-1", 300);
        bs.transfer("user-1", "user-2", 200);
        assertEquals(3100, bs.getBalance("user-1"));
        assertEquals(1700, bs.getBalance("user-2"));
    }
}