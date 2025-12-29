package com.testIC.pattern.observer;

import com.testIC.model.Transaction;

public interface TransactionObserver {
    void onTransaction(Transaction transaction);
}
