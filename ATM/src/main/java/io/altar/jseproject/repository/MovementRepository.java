package io.altar.jseproject.repository;

import java.util.List;
import java.util.Map;

import io.altar.jseproject.model.Movement;

public class MovementRepository extends EntityRepository<Movement> {

	@Override
	protected Class<Movement> getEntityClass() {
		return Movement.class;
	}

	@Override
	protected String getAllEntityQueryName() {
		return "findAllmovements";
	}

	public List<String> getCreditsDescriptionFromClientsAccount(Long id) {
		return em.createNamedQuery("getCreditsDescriptionFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}
	public Double getCreditsByDescriptionFromClientsAccounts(List <Long> accountsId, String description) {
		return em.createNamedQuery("getCreditsByDescriptionFromClientsAccounts", Double.class).setParameter("accountsId", accountsId).setParameter("description", description)
				.getSingleResult();
	}
	
	public List<String> getDebitsDescriptionFromClientsAccount(Long id) {
		return em.createNamedQuery("getDebitsDescriptionFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}
	
	public void getDebitsByDescriptionFromClientsAccounts(List <Long> accountsId, String description) {
		
		System.out.println(">>>>>>>>accountsId :"+accountsId.toString());
		System.out.println(">>>>>>>>description :"+description);

		
System.out.println( em.createNamedQuery("getDebitsByDescriptionFromClientsAccounts", Map.class).setParameter("accountsId", accountsId).setParameter("description", description)
				.getResultList());
	}
}