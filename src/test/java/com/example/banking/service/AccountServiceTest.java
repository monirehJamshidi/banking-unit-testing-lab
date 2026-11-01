package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;


    @BeforeEach
    void startUp() {
        accountService = new AccountService(accountRepository);
    }

    @Test
    void shouldCreateAccountSuccessfully() {

        Account account = new Account(
                "Monireh",
                new BigDecimal("100.00")
        );

        when(accountRepository.save(account)).thenReturn(account);

        Account actualAccount = accountService.createAccount(account);

        verify(accountRepository, times(1)).save(account);

        assertEquals(account, actualAccount);

    }

    @Test
    void shouldReturnAccountWhenAccountExists() {

        Account account = new Account(
                "Monireh",
                new BigDecimal("100.00")
        );

        account.setId(1L);
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(account);

        Account actualAccount = accountService.getAccount(accountId);

        verify(accountRepository, times(1)).findById(accountId);

        assertEquals(account, actualAccount);

    }

    @Test
    void shouldReturnNullWhenAccountDoesNotExist() {

        Long accountId = 999L;

        when(accountRepository.findById(accountId)).thenReturn(null);

        Account actualAccount = accountService.getAccount(accountId);

        verify(accountRepository, times(1)).findById(accountId);

        assertNull(actualAccount);

    }
}
