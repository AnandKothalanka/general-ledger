package com.zing.ledger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zing.ledger.entity.ReaderLedgerAccount;

public interface LedgerAccountService {
    void processAccountEvent(String message) throws JsonProcessingException;
    ReaderLedgerAccount findByAccountNumber(String accountNumber);
}
