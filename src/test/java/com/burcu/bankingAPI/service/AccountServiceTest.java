package com.burcu.bankingAPI.service;

import com.burcu.bankingAPI.entity.Account;
import com.burcu.bankingAPI.exception.AccountDeletionException;
import com.burcu.bankingAPI.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    public void deleteAccountTest()throws AccountDeletionException {
        // Arrange
        long uuid = 123L;
        Account account = new Account(uuid, 0); // Assuming 0 balance for deletion
        when(accountRepository.getAccountByUuid(uuid)).thenReturn(account);

        // Act
        String result = accountService.deleteAccount(uuid);

        // Assert
        Mockito.verify(accountRepository).deleteAccountByUuid(uuid);
        assertEquals("Account has been deleted.", result);
    }

}
