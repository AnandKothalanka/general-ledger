package com.zing.ledger.repository;

import com.zing.ledger.entity.ReaderTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderTransactionRepository extends JpaRepository<ReaderTransaction, Integer> {
}
