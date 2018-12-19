package io.altar.jseproject.repository;

import java.util.List;

import io.altar.jseproject.model.Account;

public class AccountRepository extends EntityRepository<Account> {

	@Override
	protected Class<Account> getEntityClass() {
		return Account.class;
	}
	@Override
	protected String getAllEntityQueryName() {
		return "findAllAccounts";
	}
	 public List<Account> getAccountsFromClient(Long id) {
		return em.createNamedQuery("findAccountInClient", getEntityClass()).setParameter("id", id).getResultList();
		
	}
	
}
