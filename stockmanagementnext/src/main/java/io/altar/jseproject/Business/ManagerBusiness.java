package io.altar.jseproject.Business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.EntityRepository;


public class ManagerBusiness {

	@Inject
	protected EntityRepository<Client> DBCLIENT;

	@Transactional
	public Client newClient(Client clie) {

		return DBCLIENT.addToDB(clie);

	}

	public Client findById(long id) {

		return (Client) DBCLIENT.getById(id);

	}

	public List<Client> getAll() {
		return DBCLIENT.getAll();
	}

	@Transactional
	public Client updateClient(Client Client) {

		return DBCLIENT.changeEntity(Client);
	}

	@Transactional
	public String deleteByID(long id) {
		
		return DBCLIENT.deleteEntity(id);
	}
}
