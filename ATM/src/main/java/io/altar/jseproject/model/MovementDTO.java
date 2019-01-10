package io.altar.jseproject.model;

public class MovementDTO {

	private Long id;
	private Long date;
	private String description;
	private Double debit;
	private Double credit;
	private Double balance;

	public MovementDTO(Long id, Long date, String description, Double debit, Double credit, Double balance) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}

	public MovementDTO() {
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
