package com.zing.ledger.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LedgerTransactionInput {

    private String accountId;
    private String date;
    private String description;
    private String amountStr;
    private int creditId;
    private int debitId;

}
