package com.zing.ledger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.entity.ReaderLedgerAccount;
import com.zing.ledger.event.AccountPostedEvent;
import com.zing.ledger.repository.ReaderLedgerAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LedgerAccountServiceImplTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ReaderLedgerAccountRepository accountRepository;

    @InjectMocks
    private LedgerAccountServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProcessAccountEvent() throws JsonProcessingException {

        String jsonMessage = "{\"name\":\"John Doe\",\"type\":\"DEBIT\",\"accountNumber\":\"1234567890\"}";
        AccountPostedEvent event = new AccountPostedEvent("John Doe",  "1234567890", "DEBIT");


        when(objectMapper.readValue(jsonMessage, AccountPostedEvent.class)).thenReturn(event);


        service.processAccountEvent(jsonMessage);


        verify(accountRepository, times(1)).save(any(ReaderLedgerAccount.class));
    }

    @Test
    void testFindByAccountNumber() {

        ReaderLedgerAccount account = new ReaderLedgerAccount("John Doe", ReaderLedgerAccount.Type.DEBIT, "1234567890");
        when(accountRepository.findByNumber("1234567890")).thenReturn(account);


        ReaderLedgerAccount foundAccount = service.findByAccountNumber("1234567890");


        assertEquals(account, foundAccount);
    }
}
