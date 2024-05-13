package com.zing.ledger.controller;

import com.zing.ledger.dto.LedgerAccountInput;
import com.zing.ledger.entity.LedgerAccount;
import com.zing.ledger.service.LedgerAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private LedgerAccountService accountService;

    @InjectMocks
    private AccountController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveAccount() {

        LedgerAccountInput input = new LedgerAccountInput("John Doe", "DEBIT", "1234567890");


        controller.saveAccount(input);


        verify(accountService, times(1)).saveAccount(any(LedgerAccount.class));
    }
}
