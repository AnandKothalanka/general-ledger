package com.zing.ledger.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zing.ledger.event.PostTransactionCommand;
import com.zing.ledger.event.TransactionPostedEvent;
import com.zing.ledger.kafka.KafkaEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ProcessingGroup("ledger")
@Slf4j
public class TransactionLedgerCommandHandler {

    @Autowired
    private KafkaEventPublisher kafkaEventPublisher;

    @Autowired
    ObjectMapper objectMapper;


    @CommandHandler
    public void handle(PostTransactionCommand command) throws JsonProcessingException {

        TransactionPostedEvent event = new TransactionPostedEvent(UUID.randomUUID().toString(), command.getDebitAccountId(), command.getCreditAccountId(),
                                                                  command.getAmount(), command.getDate(), command.getDescription(), command.getType());

        log.info("Posting event to query ledger for accountId {} ", command.getCreditAccountId());
        kafkaEventPublisher.publishEvent("ledger-transaction-events", objectMapper.writeValueAsString(event));
        log.info("Posted event to query ledger for accountId {} ", command.getCreditAccountId());
    }
}
