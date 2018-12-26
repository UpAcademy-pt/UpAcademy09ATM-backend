package io.altar.jseproject.Business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.EntityRepository;

//import javax.sound.sampled.Port;

public class ClientBusiness {

//transferir para conta
//levantar dinheiro
//consultar saldo: no ecrã/Por mail/imprimir talão
//
//consultar movimentos:no ecrã/Por mail/imprimir talão
	
	@Inject
	protected EntityRepository<Client> DBCLIENT;

	

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

	
}
