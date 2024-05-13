package com.zing.ledger.event;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TransactionPostedEvent {

    private String transactionId;
    private String debitAccountId;
    private String creditAccountId;
    private int amount;
    private Calendar date;
    private String description;

    public TransactionPostedEvent () {

    }

    public TransactionPostedEvent(String transactionId, String debitAccountId, String creditAccountId, int amount, Calendar date, String description, String type) {
        this.transactionId = transactionId;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    private String type;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId) {
        this.creditAccountId = creditAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

