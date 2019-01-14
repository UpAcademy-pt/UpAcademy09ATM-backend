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
	 public List<String> getBanksFromClient(Long id) {
		 List<String> list=em.createNamedQuery("getBanksFromClient", String.class).setParameter("id", id).getResultList();
		 for(String account : list) {
			 System.out.println(account);
		 }
		 return list;
		
	}
	 public Double getBankBalanceFromClient(Long id,String bank) {
		Double bankBalance=em.createNamedQuery("getBankBalanceFromClient", Double.class).setParameter("id", id).setParameter("bank", bank).getSingleResult();
		 
			 System.out.println(bank+" :"+bankBalance);
	
		 return bankBalance;
		
	}
}
