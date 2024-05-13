package com.zing.ledger.service;

import com.zing.ledger.entity.ReaderLedgerAccount;
import com.zing.ledger.entity.ReaderTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrialBalanceServiceTest {

    @Test
    void testCalculateBalance() {
        TrialBalanceService trialBalanceService = new TrialBalanceService();


        List<ReaderTransaction> transactions = new ArrayList<>();
        transactions.add(new ReaderTransaction());
        transactions.add(new ReaderTransaction());


        int balance = trialBalanceService.calculateBalance(transactions);


        assertEquals(0, balance);
    }

    @Test
    void testGetDebitTransactions() {
        TrialBalanceService trialBalanceService = new TrialBalanceService();


        List<ReaderTransaction> transactions = new ArrayList<>();

        transactions.add(ReaderTransaction.builder()
                        .debitAccount(new ReaderLedgerAccount("test", ReaderLedgerAccount.Type.DEBIT, "1234"))
                .build());
        transactions.add(ReaderTransaction.builder()
                .debitAccount(new ReaderLedgerAccount("test1", ReaderLedgerAccount.Type.DEBIT, "4321"))
                .build());

        Set<ReaderTransaction> debitTransactions = trialBalanceService.getDebitTransactions(transactions);


        assertEquals(2, debitTransactions.size());
    }

    @Test
    void testGetCreditTransactions() {
        TrialBalanceService trialBalanceService = new TrialBalanceService();


        List<ReaderTransaction> transactions = new ArrayList<>();
        transactions.add(ReaderTransaction.builder()
                .creditAccount(new ReaderLedgerAccount("test", ReaderLedgerAccount.Type.CREDIT, "1234"))
                .build());
        transactions.add(ReaderTransaction.builder()
                .creditAccount(new ReaderLedgerAccount("test", ReaderLedgerAccount.Type.CREDIT, "3421"))
                .build());


        Set<ReaderTransaction> creditTransactions = trialBalanceService.getCreditTransactions(transactions);


        assertEquals(2, creditTransactions.size());
    }
}
