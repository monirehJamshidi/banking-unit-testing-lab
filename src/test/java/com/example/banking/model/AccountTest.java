package com.example.banking.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

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

    @ParameterizedTest
    @ValueSource(strings = {"-10.00", "-100.00", "-0.01"})
    void shouldThrowExceptionWhenBalanceIsNegative(String balance) {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Account(
                        "Monireh",
                        new BigDecimal(balance)
                )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.00", "0.01", "100.00", "5000.00"})
    void shouldCreateAccountWithValidBalance(String balance) {

        Account account = new Account(
                "Monireh",
                new BigDecimal(balance)
        );

        assertEquals(new BigDecimal(balance), account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "Reza, 250.00",
            "Neda, 750.00",
            "Matyam, 0.00"
    })
    void shouldCreateAccountWithValidOwnerAndBalance(
            String owner,
            String balance) {

        Account account = new Account(
                owner,
                new BigDecimal(balance)
        );

        assertEquals(owner, account.getOwner());
        assertEquals(
                new BigDecimal(balance),
                account.getBalance()
        );
    }

    @ParameterizedTest
    @CsvSource({
            "'', 250.00",
            "' ', 750.00",
            "'    ', 0.00"
    })
    void shouldRejectBlankOwner(
            String owner,
            String balance) {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(owner, new BigDecimal(balance))
        );

        assertEquals(
                "Owner cannot be null or blank",
                exception.getMessage()
        );

    }
}
