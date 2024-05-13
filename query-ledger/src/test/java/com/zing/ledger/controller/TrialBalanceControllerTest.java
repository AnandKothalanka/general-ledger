package com.zing.ledger.controller;

import com.zing.ledger.dto.TrailBalance;
import com.zing.ledger.entity.ReaderTransaction;
import com.zing.ledger.service.LedgerTransactionService;
import com.zing.ledger.service.TrialBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TrialBalanceControllerTest {

    @Mock
    private LedgerTransactionService transactionService;

    @Mock
    private TrialBalanceService trialBalanceService;

    @InjectMocks
    private TrialBalanceController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTrialBalance() {

        List<ReaderTransaction> transactions = Arrays.asList(
                new ReaderTransaction(),
                new ReaderTransaction()
        );

        when(transactionService.getAllTransactions()).thenReturn(transactions);
        when(trialBalanceService.calculateBalance(transactions)).thenReturn(100);
        Set<ReaderTransaction> debitTransactions = new HashSet<>(transactions);
        Set<ReaderTransaction> creditTransactions = new HashSet<>(transactions);
        when(trialBalanceService.getDebitTransactions(transactions)).thenReturn(debitTransactions);
        when(trialBalanceService.getCreditTransactions(transactions)).thenReturn(creditTransactions);


        ResponseEntity<TrailBalance> response = controller.getTrialBalance();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        TrailBalance trailBalance = response.getBody();
        assertEquals(100, trailBalance.getBalance());
        assertEquals(debitTransactions, trailBalance.getDebitTransactions());
        assertEquals(creditTransactions, trailBalance.getCreditTransactions());


        verify(transactionService, times(1)).getAllTransactions();
        verify(trialBalanceService, times(1)).calculateBalance(transactions);
        verify(trialBalanceService, times(1)).getDebitTransactions(transactions);
        verify(trialBalanceService, times(1)).getCreditTransactions(transactions);
    }
}
