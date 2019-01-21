package io.altar.jseproject.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.AccountDTO;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.model.ClientDTO;
import io.altar.jseproject.repository.ClientRepository;

public class ClientBusiness extends EntityBusiness<ClientRepository, Client> {

	@Inject
	private AccountBusiness account;

	@Transactional
	@Override
	public Client newEntity(Client cli) {
		System.out.println(">>>>>>>>>>>"+cli.toString());
		System.out.println(">>>>>>>>>>>"+cli.getEmail());
		String email = cli.getEmail();
		if (repository.findClientByEmail(email).isEmpty()) {
			System.out.println("o email ainda não existe na base de dados");
			String pass = cli.getPassword();
			Integer hashPass = pass.hashCode();
			cli.setPassword(hashPass.toString());
			return repository.addToDB(cli);
		} else {
			return cli;
		}
	}

	@Transactional
	@Override
	public Client changeEntity(Client cli) {
		String pass = cli.getPassword();
		Integer hashPass = pass.hashCode();
		cli.setPassword(hashPass.toString());
		return repository.addToDB(cli);
	}

	public ClientDTO getClientById(Long id) {
		Client client = repository.getById(id);

		return getClientDTO(client);
	}

	public List<ClientDTO> getAllClient() {
		List<Client> clientList = repository.getAll();
		List<ClientDTO> clientDTOList = new ArrayList<>();
		Iterator<Client> iterator = clientList.iterator();

		while (iterator.hasNext()) {
			Client client = iterator.next();

			ClientDTO clientDTO = getClientDTO(client);

			clientDTOList.add(clientDTO);
		}
		return clientDTOList;
	}

	public ClientDTO getClientDTO(Client client) {
		ClientDTO clientDTO = new ClientDTO();

		clientDTO.setEspechial(client.getEspechial());
		clientDTO.setEmail(client.getEmail());
		clientDTO.setId(client.getId());
		clientDTO.setName(client.getName());
		clientDTO.setPassword(client.getPassword());
		clientDTO.setTel(client.getTel());
		clientDTO.setToken(client.getToken());

		return clientDTO;
	}

	@Transactional
	public List<AccountDTO> getAllAccountsFromClient(Long id) {
		System.out.println(">>>>>>>>>>antes de ir buscar o cliente");
		Client client = repository.getById(id);
		System.out.println(">>>>>>>>>>depois de ir buscar o cliente");
		List<Account> accountList = client.getAccountlist();
		System.out.println(">>>>>>>>>>depois de ir buscar a lista do cliente");

		System.out.println(accountList);

		List<AccountDTO> accountDTOList = account.generateAccountDTOListFromAccountList(accountList);
		System.out.println(accountDTOList);
		return accountDTOList;
	}

}
