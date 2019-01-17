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

	public List<Movement> findAllMovementsFromClient(Long id) {
		return em.createNamedQuery("findAllMovementsFromClient", getEntityClass()).setParameter("id", id)
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
	public List<String> getCreditsTypeFromClientsAccount(Long id) {
		return em.createNamedQuery("getCreditsTypeFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}

	public Double getCreditsByTypeFromClientsAccounts(Long id, String Type) {
		return em.createNamedQuery("getCreditsByTypeFromClientsAccounts", Double.class).setParameter("id", id)
				.setParameter("Type", Type).getSingleResult();
	}

	public List<String> getDebitsTypeFromClientsAccount(Long id) {
		return em.createNamedQuery("getDebitsTypeFromClientsAccount", String.class).setParameter("id", id)
				.getResultList();
	}

	public Double getDebitsByTypeFromClientsAccounts(Long id, String Type) {
				
				return em.createNamedQuery("getDebitsByTypeFromClientsAccounts", Double.class).setParameter("id", id).setParameter("Type", Type).getSingleResult();
	}
	}