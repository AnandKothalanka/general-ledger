package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.entity.Transaction;
import com.zing.ledger.event.PostTransactionCommand;
import com.zing.ledger.repository.LedgerTransactionRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LedgerTransactionServiceImplTest {

    @Mock
    private LedgerTransactionRepository transactionRepository;

    @Mock
    private CommandGateway commandGateway;

    @InjectMocks
    private LedgerTransactionServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveTransaction_CreditAccountPresent() {

        Calendar date = Calendar.getInstance();
        String description = "Test transaction";
        String accountId = "1234567890";
        int amount = 100;
        Optional<LedgerAccount> creditAccount = Optional.of(new LedgerAccount("abcd", LedgerAccount.Type.DEBIT, "100"));


        service.saveTransaction(date, description, accountId, amount, creditAccount, Optional.empty());


        verify(transactionRepository, times(1)).save(any(Transaction.class));


        verify(commandGateway, times(1)).sendAndWait(any(PostTransactionCommand.class));
    }

    @Test
    void testSaveTransaction_DebitAccountPresent() {

        Calendar date = Calendar.getInstance();
        String description = "Test transaction";
        String accountId = "1234567890";
        int amount = 100;
        Optional<LedgerAccount> debitAccount = Optional.of(new LedgerAccount("abcd", LedgerAccount.Type.DEBIT, "100"));


        service.saveTransaction(date, description, accountId, amount, Optional.empty(), debitAccount);


        verify(transactionRepository, times(1)).save(any(Transaction.class));


        verify(commandGateway, times(1)).sendAndWait(any(PostTransactionCommand.class));
    }


}
