package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.MovementBusiness;
import io.altar.jseproject.model.Client;
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
	public Response getEntityById(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
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
	public Response listEntity(@PathParam("token") Long token, @PathParam("expire") Long expire,
			@PathParam("espechial") Long espechial) {
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
	@Path("/findallmovementsfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response findAllMovementsFromClient(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.findAllMovementsFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.findAllMovementsFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getcreditscescriptionfromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsDescriptionFromClientsAccount(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsDescriptionFromClientsAccount(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getCreditsDescriptionFromClientsAccount(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getdebitsdescriptionfromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsDescriptionFromClientsAccount(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsDescriptionFromClientsAccount(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getDebitsDescriptionFromClientsAccount(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getdebitsbydescriptionfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsByDescriptionFromClient(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsByDescriptionFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getDebitsByDescriptionFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getcreditsbydescriptionfromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsByDescriptionFromClient(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsByDescriptionFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getCreditsByDescriptionFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getcreditstypefromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsTypeFromClientsAccount(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsTypeFromClientsAccount(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getCreditsTypeFromClientsAccount(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getdebitstypefromclientsaccount/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsTypeFromClientsAccount(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsTypeFromClientsAccount(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getDebitsTypeFromClientsAccount(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getdebitsbytypefromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getDebitsByTypeFromClient(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getDebitsByTypeFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getDebitsByTypeFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/getcreditsbytypefromclient/{id}/{token}/{expire}/{espechial}")
	@Produces("application/json")
	public Response getCreditsByTypeFromClient(@PathParam("id") Long id, @PathParam("token") Long token,
			@PathParam("expire") Long expire, @PathParam("espechial") Long espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getCreditsByTypeFromClient(id)).build();
		} else {
			if (login.verifyNormal(credential) == true) {
				Client cli = login.getClientByToken(credential);
				Long CliId = cli.getId();
				return Response.ok(business.getCreditsByTypeFromClient(CliId)).build();
			}
			return Response.serverError().entity("goToLogin").build();
		}
	}
}
