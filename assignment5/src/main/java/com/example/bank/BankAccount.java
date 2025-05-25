package com.example.bank;

import java.util.Objects;

public class BankAccount {
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = Objects.requireNonNull(accountNumber);
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance must be ≥ 0");
        }
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be > 0");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be > 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough funds");
        }
        balance -= amount;
    }

    public void transferTo(double amount, BankAccount target) {
        if (target == null) {
            throw new IllegalArgumentException("Target account must not be null");
        }
        this.withdraw(amount);
        target.deposit(amount);
    }
}
