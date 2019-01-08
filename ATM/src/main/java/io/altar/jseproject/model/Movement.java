package io.altar.jseproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllMovement", query = "SELECT c FROM Movement c")
public class Movement extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY,cascade =CascadeType.PERSIST)
	 @JoinColumn(name="account_id")
	private Account account;
	private Long date;
	private String description;
	private Double debit;
	private Double credit;
	private Double balance;

	public Movement(Account account, Long date, String description, Double debit, Double credit, Double balance) {
		super();
		this.account = account;
		this.date = date;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}

	public Movement() {
	}

	
	public Account getAccount() {
		return account;
	}


	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
