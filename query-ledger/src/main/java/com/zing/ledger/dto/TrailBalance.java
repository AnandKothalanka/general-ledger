package com.zing.ledger.dto;

import com.zing.ledger.entity.ReaderTransaction;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TrailBalance {

    private int balance;
    Set<ReaderTransaction> debitTransactions;
    Set<ReaderTransaction> creditTransactions;
}
