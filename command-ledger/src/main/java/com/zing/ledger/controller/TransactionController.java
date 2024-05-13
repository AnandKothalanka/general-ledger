package com.zing.ledger.controller;


import com.zing.ledger.dto.LedgerTransactionInput;
import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.service.LedgerAccountService;
import com.zing.ledger.service.LedgerTransactionService;
import com.zing.ledger.utils.ZingUtility;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Optional;

@RestController
@Slf4j
public class TransactionController {

    @Autowired
    LedgerTransactionService transactionService;
    @Autowired
    LedgerAccountService ledgerAccountService;


    @RequestMapping(value = "/ledger/account/transaction", method = RequestMethod.POST)
    public ResponseEntity<String> addTransaction(@RequestBody LedgerTransactionInput transactionInput) {
        Calendar cal = ZingUtility.stringToCalendar(transactionInput.getDate());
        int amount = ZingUtility.CurrencyStringToInt(transactionInput.getAmountStr());

        Optional<LedgerAccount> creditAccount = ledgerAccountService.getAccountById(transactionInput.getCreditId());
        Optional<LedgerAccount> debitAccount = ledgerAccountService.getAccountById(transactionInput.getDebitId());

        if(creditAccount.isEmpty() && debitAccount.isEmpty()) {
            return ResponseEntity.badRequest().body("Either credit or debit account is not found");
        }


        transactionService.saveTransaction(cal, transactionInput.getDescription(), transactionInput.getAccountId(), amount, creditAccount, debitAccount);

        return  ResponseEntity.ok("Success");
    }
}
