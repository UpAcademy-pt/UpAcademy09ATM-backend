package io.altar.jseproject.model;

public class AccountDTO {

	private Long id;
	private String bank;
	private Double balance;



	public AccountDTO(Long id, String bank, Double balance) {
		super();
		this.id = id;
		this.bank = bank;
		this.balance = balance;
	}


	public AccountDTO() {
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public String getBank() {
		return bank;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}


	public Double getBalance() {
		return balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}

}