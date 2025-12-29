package com.testIC.pattern.strategy;

import com.testIC.pattern.factory.AccountFactory;
import com.testIC.service.BankingService;
import com.testIC.service.TransactionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositStrategyTest {
    @Test
    public void shouldDepositMoneyCorrectly() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 3000));
        TransactionStrategy strategy = new DepositStrategy("user-1", 600);
        strategy.execute(bs);
        assertEquals(3600, bs.getBalance("user-1"));
    }
    @Test
    void depositOfZeroShouldNotChangeBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1500));
        TransactionStrategy strategy = new DepositStrategy("user-1", 0);
        strategy.execute(bs);
        assertEquals(1500, bs.getBalance("user-1"));
    }
    @Test
    void depositNegativeAmountShouldNotChangeBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1500));
        TransactionStrategy strategy = new DepositStrategy("user-1", -300);
        strategy.execute(bs);
        assertEquals(1500, bs.getBalance("user-1"));
    }
}