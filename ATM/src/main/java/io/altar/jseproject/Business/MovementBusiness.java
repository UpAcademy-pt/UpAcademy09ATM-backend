package io.altar.jseproject.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.altar.jseproject.model.Movement;
import io.altar.jseproject.model.MovementDTO;
import io.altar.jseproject.repository.MovementRepository;

public class MovementBusiness extends EntityBusiness<MovementRepository, Movement>{
	
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

	public List<MovementDTO> generateMovementDTOListFromMovementList(List<Movement> movementList){
		
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
}
