package io.altar.jseproject.Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

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
	
	public List<MovementDTO> findAllMovementsFromClient(Long id) {
		List<Movement> movementList = repository.findAllMovementsFromClient(id);
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
	public Map<String, Double> getDebitsByDescriptionFromClient(Long id) {
		Map<String, Double> debitsByDescription = new HashMap<>();

		List<String> debitsDescription = getDebitsDescriptionFromClientsAccount(id);
		System.out.println(">>>>>>>>>>>>>"+debitsDescription + debitsDescription.toString()+"<<<<<<<<<<<<");

		
		for (String description : debitsDescription) {
		
		System.out.println(">>>>>>>>e aqui???");

		Double debitByDescription= repository.getDebitsByDescriptionFromClientsAccounts(id,description);
			System.out.println(">>>>>>>>debitByDescription :"+debitByDescription);

			debitsByDescription.put(description, debitByDescription);
		}
		System.out.println(debitsByDescription.toString());
		return debitsByDescription;
	}

	
	
	@Transactional
	public Map<String, Double> getCreditsByDescriptionFromClient(Long id) {
		Map<String, Double> creditsByDescription = new HashMap<>();

		List<String> creditsDescription = getCreditsDescriptionFromClientsAccount(id);
		System.out.println(">>>>>>>>>>>>>"+creditsDescription + creditsDescription.toString()+"<<<<<<<<<<<<");

		
		for (String description : creditsDescription) {
		
		System.out.println(">>>>>>>>e aqui???");

		Double debitByDescription= repository.getCreditsByDescriptionFromClientsAccounts(id,description);
			System.out.println(">>>>>>>>dcreditByDescription :"+debitByDescription);

			creditsByDescription.put(description, debitByDescription);
		}
		System.out.println(creditsByDescription.toString());
		return creditsByDescription;
	}

}
