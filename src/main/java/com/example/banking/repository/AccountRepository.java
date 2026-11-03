package com.example.banking.repository;

import com.example.banking.model.Account;

public interface AccountRepository {

    Account save(Account account);

    Account findById(Long id);

    void deleteById(Long id);

}
