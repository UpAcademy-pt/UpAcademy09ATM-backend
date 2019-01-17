package io.altar.jseproject.model;

public class MovementDTO {

	private String bank;
	private Long accountId;
	private Long id;
	private Long date;
	private String description;
	private String type;
	private Double debit;
	private Double credit;
	private Double balance;
	
	public MovementDTO(String bank, Long accountId, Long id, Long date, String description,String type, Double debit, Double credit,
			Double balance) {
		super();
		this.bank = bank;
		this.accountId = accountId;
		this.id = id;
		this.date = date;
		this.description = description;
		this.type = type;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}

	public MovementDTO() {
	}
	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
