package com.testIC.service;

import com.testIC.model.Transaction;
import com.testIC.pattern.observer.TransactionObserver;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<TransactionObserver> observers = new ArrayList<>();
    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }
    public void notify(Transaction transaction) {
        observers.forEach(o -> o.onTransaction(transaction));
    }
}
