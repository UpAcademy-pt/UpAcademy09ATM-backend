package io.altar.jseproject.model;

public class Credential {

	private ClientDTO client;
	private Long token;
	private Long expire;
	private Long espechial;

	public Credential(ClientDTO client,Long token, Long expire, Long espechial) {
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

	public Long getToken() {
		return token;
	}

	public void setToken(Long token) {
		this.token = token;
	}

	public Long getExpire() {
		return expire;
	}

	public void setExpire(Long expire) {
		this.expire = expire;
	}

	public Long getEspechial() {
		return espechial;
	}

	public void setEspechial(Long espechial) {
		this.espechial = espechial;
	}
	
	
}
