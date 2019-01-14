package io.altar.jseproject.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.model.MovementDTO;
import io.altar.jseproject.repository.ClientRepository;
import io.altar.jseproject.repository.MovementRepository;

public class MovementBusiness extends EntityBusiness<MovementRepository, Movement> {

	@Inject
	protected ClientRepository cr;

	public MovementDTO getEntityById(Long id) {
		Movement movement = repository.getById(id);

		MovementDTO movementDTO = new MovementDTO();

		movementDTO.setId(movement.getId());
		movementDTO.setCredit(movement.getCredit());
		movementDTO.setBalance(movement.getBalance());
		movementDTO.setDate(movement.getDate());
		movementDTO.setDebit(movement.getDebit());
		movementDTO.setDescription(movement.getDescription());

		return movementDTO;
	}

	public List<MovementDTO> generateMovementDTOListFromMovementList(List<Movement> movementList) {

		List<MovementDTO> movementDTOList = new ArrayList<>();
		Iterator<Movement> iterator = movementList.iterator();

		while (iterator.hasNext()) {
			Movement movement = iterator.next();
			MovementDTO movementDTO = new MovementDTO();

			movementDTO.setId(movement.getId());
			movementDTO.setCredit(movement.getCredit());
			movementDTO.setBalance(movement.getBalance());
			movementDTO.setDate(movement.getDate());
			movementDTO.setDebit(movement.getDebit());
			movementDTO.setDescription(movement.getDescription());

			movementDTOList.add(movementDTO);
		}

		return movementDTOList;
	}

	public List<MovementDTO> getAllEntity() {
		List<Movement> movementList = repository.getAll();
		List<MovementDTO> movementDTOList = generateMovementDTOListFromMovementList(movementList);

		return movementDTOList;
	}

	@Transactional
	public List<String> getCreditsDescriptionFromClientsAccount(Long id) {

		return repository.getCreditsDescriptionFromClientsAccount(id);
	}

	@Transactional
	public List<String> getDebitsDescriptionFromClientsAccount(Long id) {

		return repository.getDebitsDescriptionFromClientsAccount(id);
	}

	@Transactional
	public String getDebitsByDescriptionFromClient(Long id) {
		List<Long> accountIdList = new ArrayList<>();
	//	Map<String, Double> debitsByDescription = new HashMap<>();

		Client client = cr.getById(id);

		List<Account> accountList = client.getAccountlist();


		for (Account account : accountList) {

			Long accountId = account.getId();
	
			accountIdList.add(accountId);
		}

		System.out.println(accountIdList);

//		List<String> debitsDescription = getDebitsDescriptionFromClientsAccount(accountIdList);
//		System.out.println(">>>>>>>>>>>>>"+debitsDescription + debitsDescription.toString()+"<<<<<<<<<<<<");
//	System.out.println(">>>>>>>>esta merda chega aqui ao menos???");
//		for (String description : debitsDescription) {
		
		System.out.println(">>>>>>>>e aqui???");

//			return repository.getDebitsByDescriptionFromClientsAccounts(accountIdList);
//			System.out.println(">>>>>>>>debitByDescription :"+debitByDescription);
//			debitsByDescription.put(description, debitByDescription);
//		}
//		System.out.println(debitsByDescription.toString());
//		return debitsByDescription;
		return "ok";
	}

}
