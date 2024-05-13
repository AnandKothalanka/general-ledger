package com.zing.ledger.event;

import lombok.*;

import java.util.Calendar;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostTransactionCommand {

    private String creditAccountId;
    private String debitAccountId;
    private Calendar date;
    private String description;
    private int amount;
    private String type;
}
