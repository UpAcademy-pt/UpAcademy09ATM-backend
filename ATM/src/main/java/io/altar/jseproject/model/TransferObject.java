package io.altar.jseproject.model;

public class TransferObject {

	private Long account1Id;
	private Long account2Id;
	private Double volume;
	private String description;


	public TransferObject() {
	}


	public Long getAccount1Id() {
		return account1Id;
	}

	public void setAccount1Id(Long account1Id) {
		this.account1Id = account1Id;
	}

	public Long getAccount2Id() {
		return account2Id;
	}

	public void setAccount2Id(Long account2Id) {
		this.account2Id = account2Id;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}