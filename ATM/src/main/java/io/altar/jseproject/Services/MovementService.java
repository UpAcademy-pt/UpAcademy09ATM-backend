package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.MovementBusiness;
import io.altar.jseproject.model.Credential;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.repository.MovementRepository;

@Path("movement")
public class MovementService extends EntityService<MovementBusiness, MovementRepository, Movement> {

	@Inject
	private LoginService login;
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEntityById(@PathParam("id") long id, Credential credential) {
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getEntityById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/listentity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntity(Credential credential) {
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getAllEntity()).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
}
