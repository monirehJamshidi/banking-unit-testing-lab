package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void shouldSaveAccountOnlyOnce() {
        Account account = new Account(
                "Monireh",
                new BigDecimal("100.00")
        );

        when(accountRepository.save(account)).thenReturn(account);

        Account actualAccount = accountService.createAccount(account);

        verify(accountRepository, times(1)).save(any(Account.class));

        assertEquals(account, actualAccount);
    }

    @Test
    void shouldNotCallSaveWhenGettingAccount() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(null);

        Account actualAccount = accountService.getAccount(accountId);

        verify(accountRepository, never()).save(any(Account.class));

        assertNull(actualAccount);
    }

    @Test
    void shouldOnlyCallFindByIdWhenGettingAccount() {

        Long accountId = 1L;

        Account account = new Account(
                "Monireh",
                new BigDecimal("100.00")
        );

        when(accountRepository.findById(accountId))
                .thenReturn(account);

        Account actualAccount = accountService.getAccount(accountId);

        assertEquals(account, actualAccount);

        verify(accountRepository).findById(accountId);

        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    void shouldCaptureAccountPassedToSave() {

        Account account = new Account(
                "Monireh",
                new BigDecimal("500.00")
        );

        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        accountService.createAccount(account);

        ArgumentCaptor<Account> captor =
                ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).save(captor.capture());

        Account capturedAccount = captor.getValue();

        assertEquals("Monireh", capturedAccount.getOwner());
        assertEquals(
                new BigDecimal("500.00"),
                capturedAccount.getBalance()
        );
    }

    @Test
    void shouldPropagateExceptionWhenRepositoryFails() {

        Long accountId = 1L;

        when(accountRepository.findById(accountId))
                .thenThrow(new RuntimeException("Database unavailable"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> accountService.getAccount(accountId)
        );

        assertEquals("Database unavailable", exception.getMessage());

        verify(accountRepository).findById(accountId);
    }

    @Test
    void shouldDeleteAccountSuccessfully(){

        Long accountId = 1L;

        accountService.deleteAccount(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
    }

    @Test
    void shouldPropagateExceptionWhenDeleteFails() {

        Long accountId = 1L;

        doThrow(new RuntimeException("Delete failed"))
                .when(accountRepository)
                .deleteById(accountId);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> {
                    accountService.deleteAccount(accountId);
                }
        );

        assertEquals("Delete failed", exception.getMessage());

        verify(accountRepository, times(1)).deleteById(accountId);
    }

    @Test
    void shouldOnlyCallDeleteByIdWhenDeletingAccount() {

        Long accountId = 1L;

        accountService.deleteAccount(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);

        verifyNoMoreInteractions(accountRepository);

    }
}
