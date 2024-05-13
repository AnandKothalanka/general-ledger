package com.zing.ledger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zing.ledger.entity.ReaderTransaction;

import java.util.List;

public interface LedgerTransactionService {

    public void processTransactionEvent(String message) throws JsonProcessingException;
    List<ReaderTransaction> getAllTransactions();
}
