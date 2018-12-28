package io.altar.jseproject.Services;

import javax.ws.rs.Path;

import io.altar.jseproject.Business.MovementBusiness;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.repository.MovementRepository;

@Path("movement")
public class MovementServices extends EntityService<MovementBusiness, MovementRepository, Movement> {


}
