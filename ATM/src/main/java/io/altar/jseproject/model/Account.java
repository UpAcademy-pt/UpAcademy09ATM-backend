package io.altar.jseproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery( name="findAllAccounts", query="SELECT a FROM Account a"),
		@NamedQuery( name="findAccountInClient", query="SELECT a FROM Account a WHERE a.user = :id")
})

public class Account extends BaseEntity {


	private static final long serialVersionUID = 1L;

	
	@ManyToOne (cascade= {CascadeType.ALL})
	private Client user;
	private Double balance;

	
	public Account(Client user, Double balance) {
		this.user = user;
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

