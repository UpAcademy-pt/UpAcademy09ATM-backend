package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.ClientBusiness;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.model.Credential;
import io.altar.jseproject.repository.ClientRepository;

@Path("client")
public class ClientService extends EntityService<ClientBusiness, ClientRepository, Client> {

	@Inject
	private LoginService login;

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEntityById(Credential credential, @PathParam("id") long id) {
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getClientById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/listentity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntity(Credential credential) {
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getAllClient()).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getallaccountsfromclient/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccountsFromClientId(Credential credential,@PathParam("id") long id) {
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getAllAccountsFromClient(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
}