package com.zing.ledger.repository;

import com.zing.ledger.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerTransactionRepository extends JpaRepository<Transaction, Integer> {
}
