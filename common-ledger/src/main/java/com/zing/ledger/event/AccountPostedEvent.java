package com.zing.ledger.event;

import org.springframework.stereotype.Component;

@Component
public class AccountPostedEvent {
    private String accountNumber;
    private String name;
    private String type;

    public AccountPostedEvent() {

    }

    public AccountPostedEvent(String accountNumber, String name, String type) {
      this.accountNumber = accountNumber;
      this.name = name;
      this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

