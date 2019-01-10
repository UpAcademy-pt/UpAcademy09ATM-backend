package io.altar.jseproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllAccounts", query = "SELECT a FROM Account a"),
		@NamedQuery(name = "findAccountInClient", query = "SELECT a FROM Account a WHERE a.user.id = :id") })

public class Account extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY,cascade =CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private Client user;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade =CascadeType.ALL,orphanRemoval=true)
	private List<Movement> movementlist;

	private Double balance;

	public Account(Client user, List<Movement> movementlist, Double balance) {
		this.user = user;
		this.movementlist = movementlist;
		this.balance = balance;
	}

	public Account() {
	}

	public Client getUser() {
		return user;
	}

	public void setUser(Client user) {
		this.user = user;
	}

	public List<Movement> getMovementlist() {
		return movementlist;
	}

	public void setMovementlist(List<Movement> movementlist) {
		this.movementlist = movementlist;
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
