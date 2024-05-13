package com.zing.ledger.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LedgerAccountInput {
    private String name;
    private String type;
    private String number;

}
