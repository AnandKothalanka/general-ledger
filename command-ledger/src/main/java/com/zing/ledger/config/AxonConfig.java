package com.zing.ledger.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public CommandBus commandBus() {
        return SimpleCommandBus.builder().build();
    }

    @Bean
    public EventStore eventStore() {
        return EmbeddedEventStore.builder().storageEngine(new InMemoryEventStorageEngine()).build();
    }
}

