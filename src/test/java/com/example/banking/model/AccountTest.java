package com.example.banking.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
