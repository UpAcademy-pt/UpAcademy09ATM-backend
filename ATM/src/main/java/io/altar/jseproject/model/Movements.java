package io.altar.jseproject.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllMovements", query = "SELECT c FROM Movements c")
public class Movements extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne (cascade= {CascadeType.ALL})
	private Account account;
	private Date date;
	private String description;
	private Double debit;
	private Double credit;
	private Double balance;
	
	public Movements() {
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDebit() {
		return debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
