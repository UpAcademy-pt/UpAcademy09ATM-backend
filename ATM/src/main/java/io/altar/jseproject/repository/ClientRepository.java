package io.altar.jseproject.repository;

import java.util.Date;

import io.altar.jseproject.model.Client;

public class ClientRepository extends EntityRepository<Client> {

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}

	Date time;
	
	@Override
	protected String getAllEntityQueryName() {
		return "findAllClients";
	}

	public Client findClientByEmail(String emailLogin) {

	return em.createNamedQuery("findClientByEmail", getEntityClass()).setParameter("emailLogin", emailLogin).getSingleResult();
	}
	
	public Client findClientByToken(Long token3) {

	return em.createNamedQuery("findClientByToken", getEntityClass()).setParameter("tokenCheck", token3).getSingleResult();		
	}
}
