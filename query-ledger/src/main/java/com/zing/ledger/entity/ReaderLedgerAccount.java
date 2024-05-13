package com.zing.ledger.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReaderLedgerAccount {

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

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditAccount")
	@JsonManagedReference
	private List<ReaderTransaction> creditEntries;

	@OneToMany(mappedBy="debitAccount")
	private List<ReaderTransaction> debitEntries;

	public ReaderLedgerAccount(String name, Type type, String number) {
		this.name = name;
		this.type = type;
		this.number = number;
	}

	@Override
	public String toString() {
		return "LedgerAccount [id=" + id + ", name=" + name + ", type=" + type + ", number=" + number + "]";
	}
}
