package com.zing.ledger.controller;



import com.zing.ledger.dto.LedgerAccountInput;
import com.zing.ledger.repository.LedgerAccountRepository;
import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.service.LedgerAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    LedgerAccountService accountService;


    @ResponseBody
    @PostMapping(path = "/ledger/account")
    public void saveAccount(@RequestBody LedgerAccountInput input) {
        LedgerAccount ledgerAccount = new LedgerAccount(input.getName(), LedgerAccount.Type.valueOf(input.getType()), input.getNumber());
        accountService.saveAccount(ledgerAccount);
    }
}
