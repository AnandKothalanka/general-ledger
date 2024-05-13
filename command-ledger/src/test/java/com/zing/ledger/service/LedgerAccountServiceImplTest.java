package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.event.PostAccountCommand;
import com.zing.ledger.repository.LedgerAccountRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LedgerAccountServiceImplTest {

    @Mock
    private LedgerAccountRepository accountRepository;

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private LedgerAccountServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAccountById() {

        int accountId = 1;
        LedgerAccount account = new LedgerAccount(/* provide test data */);
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));


        Optional<LedgerAccount> result = service.getAccountById(accountId);


        assertEquals(Optional.of(account), result);
    }

    @Test
    void testGetAccountByNumber() {

        String accountNumber = "1234567890";
        LedgerAccount account = new LedgerAccount(/* provide test data */);
        when(accountRepository.findByNumber(accountNumber)).thenReturn(account);


        LedgerAccount result = service.getAccountByNumber(accountNumber);


        assertEquals(account, result);
    }

    @Test
    void testSaveAccount() {

        LedgerAccount account = new LedgerAccount("abcd", LedgerAccount.Type.DEBIT, "100");
        when(accountRepository.save(account)).thenReturn(account);


        LedgerAccount result = service.saveAccount(account);


        verify(accountRepository, times(1)).save(account);
        verify(commandGateway, times(1)).sendAndWait(any(PostAccountCommand.class));


        assertEquals(account, result);
    }
}
