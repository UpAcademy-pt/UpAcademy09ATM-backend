package io.altar.jseproject.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllClients", query = "SELECT c FROM Client c")
public class Client extends BaseEntity {


	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String password;
	private long tel;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Account> accountlist;

	public Client() {
	}

//	@Override
//	public String toString() {
//		return "Product [iva=" + iva + ", pvp=" + pvp + ", discount=" + discountValue + "]";
//	}

	public Client(String name, String email, String password, long tel, List<Account> accountlist) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
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

	public List<Account> getAccountlist() {
		return accountlist;
	}

	public void setAccountlist(List<Account> accountlist) {
		this.accountlist = accountlist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
