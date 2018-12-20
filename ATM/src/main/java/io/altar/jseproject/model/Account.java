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

	
	@ManyToOne (cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Client user;
	private int pin;

	
	public Account() {
	}

	public Account(int pin, Client user) {
		this.pin = pin;
		this.user = user;
	}


	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
//	public String toString() {
//		return "Shelf [id=" + ShelfRepository.getIdDefinitivo() + "Capacity=" + Capacity + ", RentPrice=" + RentPrice
//				+ "Product" + productId.getId() + "]";
//	}



}

