//não existe setEspechial por segurança, só com acesso direto à base de dados se pode alterar este parâmetro

package io.altar.jseproject.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllClients", query = "SELECT c FROM Client c"),
		@NamedQuery(name = "findClientByEmail", query = "SELECT c FROM Client c WHERE c.email LIKE :emailLogin"),
		@NamedQuery(name = "findClientByToken", query = "SELECT c FROM Client c WHERE c.token LIKE :tokenCheck")
		})

public class Client extends BaseEntity {

	private static final long serialVersionUID = 1L;

//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	private List<Account> accountlist;
	private String name;
	private String email;
	private String password;
	private long tel;
	private Integer token;
	private boolean espechial;

	public Client(String name, String email, String password, long tel, Integer token,
			Date time, Boolean espechial) {
//		this.accountlist = accountlist;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.token = token;
		this.espechial=espechial;
	}

	public Client() {
	}
	
//	public List<Account> getAccountlist() {
//		return accountlist;
//	}
//
//	public void setAccountlist(List<Account> accountlist) {
//		this.accountlist = accountlist;
//	}

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

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public Integer getToken() {
		return token;
	}

	public void setToken(Integer token) {
		this.token = token;
	}

	public boolean getEspechial() {
		return espechial;
	}

	public void setEspechial(Boolean espechial) {
		this.espechial=espechial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
