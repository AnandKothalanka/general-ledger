package com.zing.ledger.event;

import com.zing.ledger.entity.LedgerAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostAccountCommand {
    private String name;
    private String type;
    private String accountNumber;
}
