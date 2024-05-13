package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;

import java.util.Calendar;
import java.util.Optional;

public interface LedgerTransactionService {

    public void saveTransaction(Calendar date, String description, String accountId, int amount, Optional<LedgerAccount> creditAccount, Optional<LedgerAccount> debitAccount);
}
