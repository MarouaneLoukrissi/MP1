package com.testIC.service;

import com.testIC.pattern.factory.AccountFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankingServiceTest {
    @Test
    void depositShouldIncreaseBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);

        bs.addAccount(AccountFactory.create("user-1", 3000));
        bs.deposit("user-1", 300);

        assertEquals(3300, bs.getBalance("user-1"));
    }
    @Test
    void depositOnUnknownAccountShouldThrowException() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        assertThrows(RuntimeException.class,
                () -> bs.deposit("unknown", 300));
    }
    @Test
    void transferToSameAccountShouldNotChangeBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 1500));
        bs.transfer("user-1", "user-1", 600);
        assertEquals(1500, bs.getBalance("user-1"));
    }
    @Test
    void depositThenWithdrawShouldRestoreBalance() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 3000));
        bs.deposit("user-1", 600);
        bs.withdraw("user-1", 600);
        assertEquals(3000, bs.getBalance("user-1"));
    }
    @Test
    void multipleDepositsShouldAccumulateCorrectly() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 100));
        bs.deposit("user-1", 600);
        bs.deposit("user-1", 1800);
        bs.deposit("user-1", 2700);
        assertEquals(5200, bs.getBalance("user-1"));
    }
    @Test
    void multipleWithdrawalsShouldAccumulateCorrectly() {
        TransactionService ts = new TransactionService();
        BankingService bs = new BankingService(ts);
        bs.addAccount(AccountFactory.create("user-1", 3000));
        bs.withdraw("user-1", 300);
        bs.withdraw("user-1", 600);
        assertEquals(2100, bs.getBalance("user-1"));
    }
}