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
	@Path("/{id}/{token}/{expire}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEntityById(@PathParam("id") Long id, @PathParam("token") Long token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getClientById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}


	@GET
	@Path("/listentity/{token}/{expire}/{espechial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntity(@PathParam("token") Long token, @PathParam("expire") Long expire,
			@PathParam("espechial") Long espechial) {
		System.out.println(">>>>>>>>>>>chegaste aqui ao menos???");
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getAllClient()).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getallaccountsfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccountsFromClientId(@PathParam("id") Long id,@PathParam("token") Long token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getAllAccountsFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getAllAccountsFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}
}