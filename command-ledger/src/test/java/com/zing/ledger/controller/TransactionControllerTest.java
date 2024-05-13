package com.zing.ledger.controller;

import com.zing.ledger.dto.LedgerTransactionInput;
import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.service.LedgerAccountService;
import com.zing.ledger.service.LedgerTransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @Mock
    private LedgerTransactionService transactionService;

    @Mock
    private LedgerAccountService ledgerAccountService;

    @InjectMocks
    private TransactionController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTransaction() {

        LedgerTransactionInput transactionInput = new LedgerTransactionInput("123","2024-05-13","desc","100",1,2);
        Calendar cal = Calendar.getInstance();
        int amount = 100;
        Optional<LedgerAccount> creditAccount = Optional.of(new LedgerAccount());
        Optional<LedgerAccount> debitAccount = Optional.of(new LedgerAccount());


        when(ledgerAccountService.getAccountById(anyInt())).thenReturn(creditAccount).thenReturn(debitAccount);


        ResponseEntity<String> response = controller.addTransaction(transactionInput);


        //verify(transactionService, times(1)).saveTransaction(any(), anyString(), anyString(), any(), any(), any());


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    void testAddTransaction_AccountNotFound() {

        LedgerTransactionInput transactionInput = new LedgerTransactionInput("123","2024-05-13","desc","100",1,2);

        when(ledgerAccountService.getAccountById(anyInt())).thenReturn(Optional.empty());


        ResponseEntity<String> response = controller.addTransaction(transactionInput);


        verify(transactionService, never()).saveTransaction(any(), any(), any(), anyInt(), any(), any());


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Either credit or debit account is not found", response.getBody());
    }
}
