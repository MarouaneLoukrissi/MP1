package com.testIC.pattern.observer;

import com.testIC.model.Transaction;
import com.testIC.service.TransactionService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionServiceTest {
    @Test
    public void shouldNotifyObserverWhenTransactionOccurs() {
        TransactionService service = new TransactionService();
        TestObserver observer = new TestObserver();
        service.addObserver(observer);
        service.notify(new Transaction("Test transaction"));
        assertTrue(observer.isNotified());
    }
    @Test
    void shouldNotifyAllObservers() {
        TransactionService service = new TransactionService();
        TestObserver obs1 = new TestObserver();
        TestObserver obs2 = new TestObserver();
        service.addObserver(obs1);
        service.addObserver(obs2);
        service.notify(new Transaction("Test"));
        assertTrue(obs1.notified);
        assertTrue(obs2.notified);
    }
    static class TestObserver implements TransactionObserver {
        private boolean notified = false;
        @Override
        public void onTransaction(Transaction transaction) {
            notified = true;
        }
        public boolean isNotified() {
            return notified;
        }
    }
}