package io.altar.jseproject.repository;

import io.altar.jseproject.model.Client;

public class ClientRepository extends EntityRepository<Client> {

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}
	
	@Override
	protected String getAllEntityQueryName() {
		return "findAllClients";

	
	}
}
