package com.zing.ledger.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.event.AccountPostedEvent;
import com.zing.ledger.event.PostAccountCommand;
import com.zing.ledger.kafka.KafkaEventPublisher;
import com.zing.ledger.utils.ZingUtility;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("ledger")
@Slf4j
public class AccountLedgerCommandHandler {

    @Autowired
    private KafkaEventPublisher kafkaEventPublisher;

    @Autowired
    ObjectMapper objectMapper;


    @CommandHandler
    public void handle(PostAccountCommand command) throws JsonProcessingException {

        AccountPostedEvent event = new AccountPostedEvent(command.getAccountNumber(), command.getName(), command.getType() );

        log.info("Posting event to query ledger for creating account accountId {} ", command.getAccountNumber());
        kafkaEventPublisher.publishEvent("ledger-account-events", objectMapper.writeValueAsString(event));
        log.info("Posted event to query ledger for creating account accountId {} ", command.getAccountNumber());
    }
}
