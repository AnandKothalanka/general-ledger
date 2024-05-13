package com.zing.ledger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.entity.ReaderLedgerAccount;
import com.zing.ledger.entity.ReaderTransaction;
import com.zing.ledger.event.TransactionPostedEvent;
import com.zing.ledger.repository.ReaderTransactionRepository;
import org.apache.commons.validator.routines.CalendarValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LedgerTransactionServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ReaderTransactionRepository transactionRepository;

    @Mock
    private LedgerAccountService accountService;

    @InjectMocks
    private LedgerTransactionServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessTransactionEvent() throws JsonProcessingException {

        String jsonMessage = "{\"creditAccountId\":\"1234567890\",\"debitAccountId\":\"0987654321\",\"amount\":100.0,\"description\":\"Test Transaction\",\"date\":\"2024-05-13T12:00:00\"}";
        TransactionPostedEvent event = new TransactionPostedEvent("1234","1234567890", "0987654321", 100, Calendar.getInstance(), "Test Transaction", "DEBIT");


        when(objectMapper.readValue(jsonMessage, TransactionPostedEvent.class)).thenReturn(event);


        ReaderLedgerAccount creditAccount = new ReaderLedgerAccount("Credit Account", ReaderLedgerAccount.Type.DEBIT, "1234567890");
        ReaderLedgerAccount debitAccount = new ReaderLedgerAccount("Debit Account", ReaderLedgerAccount.Type.DEBIT, "0987654321");
        when(accountService.findByAccountNumber("1234567890")).thenReturn(creditAccount);
        when(accountService.findByAccountNumber("0987654321")).thenReturn(debitAccount);


        service.processTransactionEvent(jsonMessage);


        verify(transactionRepository, times(1)).save(any(ReaderTransaction.class));
    }

    @Test
    void testGetAllTransactions() {

        ReaderTransaction transaction = new ReaderTransaction(/* transaction data */);
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));

        List<ReaderTransaction> transactions = service.getAllTransactions();


        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }
}
