package io.altar.jseproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "findAllmovements", query = "SELECT c FROM Movement c"),
	@NamedQuery(name = "findAllMovementsFromClient", query = "SELECT a FROM Movement a WHERE a.account.user.id = :id"),
	@NamedQuery(name = "getCreditsDescriptionFromClientsAccount", query = "SELECT DISTINCT (a.description) FROM Movement a WHERE a.account.user.id = :id AND a.debit = 0"),
	@NamedQuery(name = "getDebitsDescriptionFromClientsAccount", query = "SELECT DISTINCT (a.description) FROM Movement a WHERE a.account.user.id = :id AND a.credit = 0"),
	@NamedQuery(name = "getCreditsByDescriptionFromClientsAccounts", query = "SELECT SUM (a.credit) FROM Movement a WHERE a.account.user.id= :id AND a.debit = 0 AND a.description= :description"),
	@NamedQuery(name = "getDebitsByDescriptionFromClientsAccounts", query = "SELECT SUM (a.debit) FROM Movement a WHERE a.account.user.id= :id AND a.credit = 0 AND a.description= :description"),


})
public class Movement extends BaseEntity {
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "account_id")
	private Account account;
	private Long date;
	private String description;
	private Double debit;
	private Double credit;
	private Double balance;

	public Movement(Account account, Long date, String description, Double debit, Double credit, Double balance) {
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
