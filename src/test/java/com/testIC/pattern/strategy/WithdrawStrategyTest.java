package com.testIC.pattern.strategy;

import com.testIC.pattern.factory.AccountFactory;
import com.testIC.service.BankingService;
import com.testIC.service.TransactionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WithdrawStrategyTest {
    @Test
    public void shouldWithdrawMoneyIfBalanceIsEnough() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 3000));
        TransactionStrategy strategy = new WithdrawStrategy("user-1", 900);
        strategy.execute(bs);
        assertEquals(2100, bs.getBalance("user-1"));
    }
    @Test
    void shouldNotWithdrawIfBalanceIsInsufficient() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 300));
        TransactionStrategy strategy = new WithdrawStrategy("user-1", 1500);
        strategy.execute(bs);
        assertEquals(300, bs.getBalance("user-1"));
    }
    @Test
    void withdrawZeroShouldNotChangeBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1200));
        TransactionStrategy strategy = new WithdrawStrategy("user-1", 0);
        strategy.execute(bs);
        assertEquals(1200, bs.getBalance("user-1"));
    }
    @Test
    void withdrawNegativeAmountShouldNotChangeBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1200));
        TransactionStrategy strategy = new WithdrawStrategy("user-1", -150);
        strategy.execute(bs);
        assertEquals(1200, bs.getBalance("user-1"));
    }
}