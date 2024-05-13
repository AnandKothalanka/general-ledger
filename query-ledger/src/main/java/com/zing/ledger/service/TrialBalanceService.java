package com.zing.ledger.service;

import com.zing.ledger.entity.ReaderTransaction;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TrialBalanceService {
	
	public int calculateBalance(List<ReaderTransaction> transactions) {
		int debitBalance = 0;
		int creditBalance = 0;
		
		for (ReaderTransaction transaction : transactions) {
			if (transaction.getDebitAccount() != null) {
				debitBalance += transaction.getAmount();
			}
			if (transaction.getCreditAccount() != null) {
				creditBalance += transaction.getAmount();
			}
		}
		return debitBalance - creditBalance;
	}
	

	public Set<ReaderTransaction> getDebitTransactions(List<ReaderTransaction> transactions) {
		Set<ReaderTransaction> debitTransactions = new HashSet<ReaderTransaction>();
		for (ReaderTransaction transaction : transactions) {
			if (transaction.getDebitAccount() != null) {
				debitTransactions.add(transaction);
			}
		}
		return debitTransactions;
	}
	
	public Set<ReaderTransaction> getCreditTransactions(List<ReaderTransaction> transactions) {
		Set<ReaderTransaction> creditTransactions = new HashSet<ReaderTransaction>();
		for (ReaderTransaction transaction : transactions) {
			if (transaction.getCreditAccount() != null) {
				creditTransactions.add(transaction);
			}
		}
		return creditTransactions;
	}
}
