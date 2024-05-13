package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.entity.Transaction;
import com.zing.ledger.event.PostTransactionCommand;
import com.zing.ledger.repository.LedgerTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@Slf4j
public class LedgerTransactionServiceImpl implements LedgerTransactionService {

    @Autowired
    LedgerTransactionRepository transactionRepository;

    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void saveTransaction(Calendar date, String description, String accountId, int amount, Optional<LedgerAccount> creditAccount, Optional<LedgerAccount> debitAccount) {
        String type = null;
        String creditAccountNumber = null;
        String debitAccountNumber = null;
        if(creditAccount.isPresent()) {
            type = creditAccount.get().getType().toString();
            creditAccountNumber = creditAccount.get().getNumber();
        } else if (debitAccount.isPresent()) {
            type = debitAccount.get().getType().toString();
            debitAccountNumber = debitAccount.get().getNumber();
        }


        Transaction transaction = Transaction.builder()
                .date(Calendar.getInstance())
                .amount(amount)
                .creditAccount(creditAccount.orElse(null))
                .debitAccount(debitAccount.orElse(null))
                .build();
        transactionRepository.save(transaction);


        log.info("Transaction saved for accountId {} , sending command", accountId);
        commandGateway.sendAndWait(PostTransactionCommand.builder()
                       .creditAccountId(creditAccountNumber)
                       .debitAccountId(debitAccountNumber)
                       .date(date)
                       .description(description)
                       .amount(amount)
                       .type(type)
               .build());
    }
}
