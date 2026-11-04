package com.example.banking.model;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private final String owner;
    private final BigDecimal balance;

    public Account(String owner, BigDecimal balance){

        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException(
                    "Owner cannot be null or blank"
            );
        }

        if (balance == null) {
            throw new IllegalArgumentException(
                    "Balance cannot be null"
            );
        }

        if (balance.signum() < 0) {
            throw new IllegalArgumentException(
                    "Balance cannot be negative"
            );
        }

        this.owner = owner;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}
