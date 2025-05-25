package com.example.bank;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
    private BankAccount account;
    private BankAccount other;

    @BeforeEach
    void setUp() {
        account = new BankAccount("ACC-001", 100.0);
        other   = new BankAccount("ACC-002", 50.0);
    }

    @Test
    void initialBalanceAndNumber() {
        assertEquals("ACC-001", account.getAccountNumber());
        assertEquals(100.0, account.getBalance(), 1e-6);
    }

    // --- deposit ---

    @Test
    void depositPositiveIncreasesBalance() {
        account.deposit(25.5);
        assertEquals(125.5, account.getBalance(), 1e-6);
    }

    @Test
    void depositZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
    }

    @Test
    void depositNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
    }

    // --- withdraw ---

    @Test
    void withdrawValidDecreasesBalance() {
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 1e-6);
    }

    @Test
    void withdrawZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
    }

    @Test
    void withdrawNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
    }

    @Test
    void withdrawMoreThanBalanceThrows() {
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(150.0));
    }

    // --- transfer ---

    @Test
    void transferValidMovesMoney() {
        account.transferTo(30.0, other);
        assertAll(
            () -> assertEquals(70.0, account.getBalance(), 1e-6),
            () -> assertEquals(80.0, other.getBalance(),   1e-6)
        );
    }

    @Test
    void transferZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.transferTo(0, other));
    }

    @Test
    void transferInsufficientFundsThrows() {
        assertThrows(InsufficientFundsException.class, () -> account.transferTo(200, other));
    }

    @Test
    void transferToNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> account.transferTo(10, null));
    }
}
