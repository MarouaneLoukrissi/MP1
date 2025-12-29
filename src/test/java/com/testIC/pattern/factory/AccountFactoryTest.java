package com.testIC.pattern.factory;

import com.testIC.model.Account;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountFactoryTest {
    @Test
    void shouldCreateAccountWithInitialBalance() {
        Account acc = AccountFactory.create("user-1", 3000);
        assertEquals("user-1", acc.getOwner());
        assertEquals(3000, acc.getBalance());
    }
    @Test
    void initialBalanceCanBeZero() {
        Account acc = AccountFactory.create("user-0", 0);
        assertEquals(0, acc.getBalance());
    }
    @Test
    void negativeInitialBalanceIsNotAllowed() {
        assertThrows(IllegalArgumentException.class, () -> AccountFactory.create("user--300", -300));
    }
}