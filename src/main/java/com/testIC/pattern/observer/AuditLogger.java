package com.testIC.pattern.observer;

import com.testIC.model.Transaction;
import com.testIC.pattern.singleton.ConfigurationManager;

public class AuditLogger implements TransactionObserver {
    private final ConfigurationManager config = ConfigurationManager.getInstance();
    @Override
    public void onTransaction(Transaction transaction) {
        if ("PRODUCTION".equals(config.getEnvironment())) {
            System.out.println("{AUDIT} " + transaction);
        }
    }
}
