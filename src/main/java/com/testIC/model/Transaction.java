package com.testIC.model;

import java.time.LocalDateTime;

public class Transaction {
    private final String description;
    private final LocalDateTime date;
    public Transaction(String description) {
        this.description = description;
        this.date = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return date + " -> " + description;
    }
}
