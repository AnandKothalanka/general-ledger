package com.zing.ledger.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LedgerAccount {
	
	public enum Type {
		CREDIT,
		DEBIT,
	}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;

	@Column
	private String name;

	@Column
    @Enumerated(EnumType.STRING)
	private Type type;

	@Column(unique=true)
	private String number;
	
	@OneToMany(mappedBy="creditAccount")
	private List<Transaction> creditEntries;
	
	@OneToMany(mappedBy="debitAccount")
	private List<Transaction> debitEntries;

	public LedgerAccount(String name, Type type, String number) {
		this.name = name;
		this.type = type;
		this.number = number;
	}

	@Override
	public String toString() {
		return "LedgerAccount [id=" + id + ", name=" + name + ", type=" + type + ", number=" + number + "]";
	}
}
