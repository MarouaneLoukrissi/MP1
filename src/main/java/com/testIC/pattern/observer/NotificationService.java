package com.testIC.pattern.observer;

import com.testIC.model.Transaction;
import com.testIC.pattern.singleton.ConfigurationManager;

public class NotificationService implements TransactionObserver {
    private final ConfigurationManager config = ConfigurationManager.getInstance();
    @Override
    public void onTransaction(Transaction transaction) {
        if (config.isNotificationsEnabled()) {
            System.out.println("{NOTIFICATION} " + transaction);
        }
    }
}