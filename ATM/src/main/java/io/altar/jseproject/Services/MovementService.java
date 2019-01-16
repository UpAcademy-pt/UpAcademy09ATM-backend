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
	@Path("/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getEntityById(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getEntityById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/listentity/{token}/{expire}/{espechial}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntity(@PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getAllEntity()).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@GET
	@Path("getcreditscescriptionfromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsDescriptionFromClientsAccount(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsDescriptionFromClientsAccount(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@GET
	@Path("getdebitsdescriptionfromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsDescriptionFromClientsAccount(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsDescriptionFromClientsAccount(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	
	@GET
	@Path("getdebitsbydescriptionfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsByDescriptionFromClient(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsByDescriptionFromClient(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@GET
	@Path("getcreditsbydescriptionfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsByDescriptionFromClient(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsByDescriptionFromClient(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
}
