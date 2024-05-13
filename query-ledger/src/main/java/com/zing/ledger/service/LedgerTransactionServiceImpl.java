package com.zing.ledger.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.entity.ReaderLedgerAccount;
import com.zing.ledger.entity.ReaderTransaction;
import com.zing.ledger.event.TransactionPostedEvent;
import com.zing.ledger.repository.ReaderTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedgerTransactionServiceImpl implements LedgerTransactionService{
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ReaderTransactionRepository transactionRepository;
    @Autowired
    LedgerAccountService accountService;


    @Override
    public void processTransactionEvent(String message) throws JsonProcessingException {
        TransactionPostedEvent transactionPostedEvent = objectMapper.readValue(message, TransactionPostedEvent.class);
        ReaderLedgerAccount creditAccount = accountService.findByAccountNumber(transactionPostedEvent.getCreditAccountId());
        ReaderLedgerAccount debitAccount = accountService.findByAccountNumber(transactionPostedEvent.getDebitAccountId());
        transactionRepository.save(ReaderTransaction.builder()
                        .amount(transactionPostedEvent.getAmount())
                        .creditAccount(creditAccount)
                        .debitAccount(debitAccount)
                        .description(transactionPostedEvent.getDescription())
                        .date(transactionPostedEvent.getDate())
                .build());
    }

    @Override
    public List<ReaderTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
