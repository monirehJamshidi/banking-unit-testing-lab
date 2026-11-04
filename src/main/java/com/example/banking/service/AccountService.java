package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;

import java.util.Objects;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
