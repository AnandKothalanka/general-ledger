package com.zing.ledger.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.NumberFormat;
import java.util.Calendar;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column
	private Calendar date;

	@Column
	private String description;

	@Column
	private int amount;
	
	//the credit entry for this transaction
	@ManyToOne
	@JoinColumn(name = "credit_account_id")
	private LedgerAccount creditAccount;
	
	//the debit entry for this transaction
	@ManyToOne
	@JoinColumn(name = "debit_account_id")
	private LedgerAccount debitAccount;
	
	public String amountToString() {
		NumberFormat n = NumberFormat.getCurrencyInstance();
		return n.format(amount / 100.0);
	}
}
