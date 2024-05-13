package com.zing.ledger.controller;

import com.zing.ledger.dto.TrailBalance;
import com.zing.ledger.entity.ReaderTransaction;
import com.zing.ledger.service.LedgerTransactionService;
import com.zing.ledger.service.TrialBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class TrialBalanceController {

    @Autowired
    LedgerTransactionService transactionService;
    @Autowired
    TrialBalanceService trialBalanceService;

    @RequestMapping(value = "/ledger/trialbalance", method = RequestMethod.GET)
    public ResponseEntity<TrailBalance> getTrialBalance() {
        List<ReaderTransaction> transactions = transactionService.getAllTransactions();
        int balance = trialBalanceService.calculateBalance(transactions);
        Set<ReaderTransaction> debitTransactions =
                trialBalanceService.getDebitTransactions(transactions);
        Set<ReaderTransaction> creditTransactions =
                trialBalanceService.getCreditTransactions(transactions);

        return ResponseEntity.ok(TrailBalance.builder()
                        .balance(balance)
                        .debitTransactions(debitTransactions)
                        .creditTransactions(creditTransactions)
                .build());
    }
}
