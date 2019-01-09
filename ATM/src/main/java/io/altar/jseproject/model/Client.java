//não existe setEspechial por segurança, só com acesso direto à base de dados se pode alterar este parâmetro

package io.altar.jseproject.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "findAllClients", query = "SELECT c FROM Client c"),
		@NamedQuery(name = "findClientByEmail", query = "SELECT c FROM Client c WHERE c.email LIKE :emailLogin"),
		@NamedQuery(name = "findClientByToken", query = "SELECT c FROM Client c WHERE c.token LIKE :tokenCheck")
		})

public class Client extends BaseEntity {

	private static final long serialVersionUID = 1L;


	private String name;
	private String email;
	private String password;
	private long tel;
	private Integer token;
	private boolean espechial;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Account> accountlist;
	
	public Client(String name, String email, String password, long tel, Integer token,
			Date time, Boolean espechial,List<Account> accountlist) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.token = token;
		this.espechial=espechial;
		this.accountlist = accountlist;

	}

	public Client() {
	}
	
	public List<Account> getAccountlist() {
		return accountlist;
	}

	public void setAccountlist(List<Account> accountlist) {
		this.accountlist = accountlist;
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
