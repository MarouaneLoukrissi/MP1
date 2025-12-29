package com.testIC.pattern.factory;

import com.testIC.model.Account;

public class AccountFactory {
    public static Account create(String owner, double initialBalance) {
        if(initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance can't be negative");
        }
        return new Account(owner, initialBalance);
    }
}
