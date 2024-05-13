package com.zing.ledger.repository;

import com.zing.ledger.entity.LedgerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerAccountRepository extends JpaRepository<LedgerAccount, Integer> {

    LedgerAccount findByNumber(String number);
}
