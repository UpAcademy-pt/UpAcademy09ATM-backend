package io.altar.jseproject.model;

public class Credential {

	private ClientDTO client;
	private Integer token;
	private Long expire;
	private Integer espechial;

	public Credential(ClientDTO client,Integer token, Long expire, Integer espechial) {
		this.client=client;
		this.token = token;
		this.expire = expire;
		this.espechial = espechial;
	}

	public Credential() {
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public Integer getEspechial() {
		return espechial;
	}

	public void setEspechial(Integer espechial) {
		this.espechial = espechial;
	}
	
	
}
