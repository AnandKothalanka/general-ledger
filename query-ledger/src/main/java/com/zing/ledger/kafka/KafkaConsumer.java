package com.zing.ledger.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zing.ledger.service.LedgerAccountService;
import com.zing.ledger.service.LedgerTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    LedgerAccountService accountService;
    @Autowired
    LedgerTransactionService transactionService;

    @KafkaListener(topics = "ledger-account-events")
    public void consumeAccountEvents(String message) {
        log.info("Consumer consume accounts Kafka message -> {}", message);
        try {
            accountService.processAccountEvent(message);
        } catch (JsonProcessingException e) {
            log.error("Error consuming ledger account event ", e);
        }

    }

    @KafkaListener(topics = "ledger-transaction-events")
    public void consumeTransactionEvents(String message) {
        log.info("Consumer consume transaction Kafka message -> {}", message);
        try {
            transactionService.processTransactionEvent(message);
        } catch (JsonProcessingException e) {
            log.error("Error consuming ledger transaction event ", e);
        }

    }
}
