package com.zing.ledger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.entity.ReaderLedgerAccount;
import com.zing.ledger.event.AccountPostedEvent;
import com.zing.ledger.repository.ReaderLedgerAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LedgerAccountServiceImpl implements LedgerAccountService{

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ReaderLedgerAccountRepository accountRepository;

    @Override
    public void processAccountEvent(String message) throws JsonProcessingException {
        AccountPostedEvent accountPostedEvent = objectMapper.readValue(message, AccountPostedEvent.class);

        ReaderLedgerAccount readerLedgerAccount = new ReaderLedgerAccount(accountPostedEvent.getName(), ReaderLedgerAccount.Type.valueOf(accountPostedEvent.getType()), accountPostedEvent.getAccountNumber());
        accountRepository.save(readerLedgerAccount);
    }

    @Override
    public ReaderLedgerAccount findByAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }
}
