package com.example.banking.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    void shouldCreateAccountWithValidOwnerAndBalance() {

        // TODO: Create an Account
        Account account = new Account("Monireh", new BigDecimal("100.00"));

        // TODO: Assert the owner
        assertEquals("Monireh",account.getOwner());

        // TODO: Assert the balance
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsNegative() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account("Monireh", new BigDecimal("-10.00"))
        );

        assertEquals(
                "Balance cannot be negative",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsBlank() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(" ", new BigDecimal("100.00"))
        );

        assertEquals(
                "Owner cannot be null or blank",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenOwnerIsNull() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(null, new BigDecimal("100.00"))
        );

        assertEquals(
                "Owner cannot be null or blank",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowExceptionWhenBalanceIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account("Monireh", null)
        );

        assertEquals(
                "Balance cannot be null",
                exception.getMessage()
        );
    }

    @Test
    void shouldCreateAccountWithZeroBalance() {
        Account account = new Account("Monireh", new BigDecimal("0.00"));

        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }
}
