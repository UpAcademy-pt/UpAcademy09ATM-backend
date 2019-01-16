package io.altar.jseproject.repository;

import java.util.List;

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

	public List<Movement> findAllmovementsFromClient(Long id) {
		return em.createNamedQuery("findAllmovementsFromClient", getEntityClass()).setParameter("id", id)
				.getResultList();
	}

	public List<String> getCreditsDescriptionFromClientsAccount(Long id) {
		return em.createNamedQuery("getCreditsDescriptionFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}

	public Double getCreditsByDescriptionFromClientsAccounts(Long id, String description) {
		return em.createNamedQuery("getCreditsByDescriptionFromClientsAccounts", Double.class).setParameter("id", id)
				.setParameter("description", description).getSingleResult();
	}

	public List<String> getDebitsDescriptionFromClientsAccount(Long id) {
		return em.createNamedQuery("getDebitsDescriptionFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}

	public Double getDebitsByDescriptionFromClientsAccounts(Long id, String description) {
				
				return em.createNamedQuery("getDebitsByDescriptionFromClientsAccounts", Double.class).setParameter("id", id).setParameter("description", description).getSingleResult();
	}
}