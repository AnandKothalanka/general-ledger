package com.zing.ledger.service;

import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.event.PostAccountCommand;
import com.zing.ledger.event.PostTransactionCommand;
import com.zing.ledger.repository.LedgerAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@Slf4j
public class LedgerAccountServiceImpl implements LedgerAccountService{

    @Autowired
    LedgerAccountRepository accountRepository;
    @Autowired
    CommandGateway commandGateway;
    @Override
    public Optional<LedgerAccount> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public LedgerAccount getAccountByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public LedgerAccount saveAccount(LedgerAccount account) {
        LedgerAccount save = accountRepository.save(account);

        log.info("Account saved -- accountId {} , sending command", account.getNumber());
        commandGateway.sendAndWait(PostAccountCommand.builder()
                .name(account.getName())
                .accountNumber(account.getNumber())
                .type(account.getType().toString())
                .build());

        return save;
    }
}
