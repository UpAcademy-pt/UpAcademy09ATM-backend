package io.altar.jseproject.Business;

import javax.transaction.Transactional;

import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.ClientRepository;

public class ClientBusiness extends EntityBusiness<ClientRepository, Client> {

	@Transactional
	@Override
	public Client newEntity(Client cli) {
		String pass = cli.getPassword();
		Integer hashPass = pass.hashCode();
		cli.setPassword(hashPass.toString());
		return repository.addToDB(cli);
	}
	@Transactional
	@Override
	public Client changeEntity(Client cli) {
		String pass = cli.getPassword();
		Integer hashPass = pass.hashCode();
		cli.setPassword(hashPass.toString());
		return repository.addToDB(cli);
	}
}
