package com.testIC.pattern.strategy;

import com.testIC.pattern.factory.AccountFactory;
import com.testIC.service.BankingService;
import com.testIC.service.TransactionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferStrategyTest {
    @Test
    public void shouldTransferMoneyBetweenAccounts() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1000));
        bs.addAccount(AccountFactory.create("user-2", 500));
        TransactionStrategy strategy = new TransferStrategy("user-1", "user-2", 200);
        strategy.execute(bs);
        assertEquals(800, bs.getBalance("user-1"));
        assertEquals(700, bs.getBalance("user-2"));
    }
    @Test
    void shouldNotTransferIfBalanceIsInsufficient() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 100));
        bs.addAccount(AccountFactory.create("user-2", 500));
        TransactionStrategy strategy = new TransferStrategy("user-1", "user-2", 300);
        strategy.execute(bs);
        assertEquals(100, bs.getBalance("user-1"));
        assertEquals(500, bs.getBalance("user-2"));
    }
}