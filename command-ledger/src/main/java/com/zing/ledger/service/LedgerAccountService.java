package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;

import java.util.Optional;

public interface LedgerAccountService {
    Optional<LedgerAccount> getAccountById(int id);
    LedgerAccount getAccountByNumber(String number);
    LedgerAccount saveAccount(LedgerAccount account);
}
