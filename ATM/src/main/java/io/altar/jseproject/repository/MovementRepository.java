package io.altar.jseproject.repository;

import io.altar.jseproject.model.Movements;

public class MovementRepository extends EntityRepository<Movements> {

	@Override
	protected Class<Movements> getEntityClass() {
		return Movements.class;
	}
	
	@Override
	protected String getAllEntityQueryName() {
		return "findAllmovements";

	
	}
}