package com.testIC.model;

public class Account {
    private final String owner;
    private double balance;
    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
    public double getBalance() { return balance; }
    public String getOwner() { return owner; }
    public void credit(double amount) { balance += amount; }
    public void debit(double amount) { balance -= amount; }
}
