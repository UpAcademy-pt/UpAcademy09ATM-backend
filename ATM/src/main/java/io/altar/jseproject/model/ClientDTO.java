package io.altar.jseproject.model;

import java.util.Date;

public class ClientDTO {

	private Long id;
	private String name;
	private String email;
	private String password;
	private Long tel;
	private Long token;
	private Boolean espechial;

	public ClientDTO(Long id, String name, String email, String password, Long tel, Long token, Date time,
			Boolean espechial) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.token = token;
		this.espechial = espechial;

	}

	public ClientDTO() {
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public Long getToken() {
		return token;
	}

	public void setToken(Long token) {
		this.token = token;
	}

	public Boolean getEspechial() {
		return espechial;
	}

	public void setEspechial(Boolean espechial) {
		this.espechial = espechial;
	}
}
