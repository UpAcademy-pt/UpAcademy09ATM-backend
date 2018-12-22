package io.altar.jseproject.repository;

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
}