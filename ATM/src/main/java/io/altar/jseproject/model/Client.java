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
		@NamedQuery(name = "findClientByToken", query = "SELECT c FROM Client c WHERE c.token LIKE :tokencheck")
		})

public class Client extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Account> accountlist;
	private String name;
	private String email;
	private String password;
	private long tel;
	private Integer token;
	private Date time;

	public Client(List<Account> accountlist, String name, String email, String password, long tel, Integer token,
			Date time) {
		super();
		this.accountlist = accountlist;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.token = token;
		this.time = time;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
