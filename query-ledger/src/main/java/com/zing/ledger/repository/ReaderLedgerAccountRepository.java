package com.zing.ledger.repository;

import com.zing.ledger.entity.ReaderLedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderLedgerAccountRepository extends JpaRepository<ReaderLedgerAccount, Integer> {

    ReaderLedgerAccount findByNumber(String accountNumber);
}
