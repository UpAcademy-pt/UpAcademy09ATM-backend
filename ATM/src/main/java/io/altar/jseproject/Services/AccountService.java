package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.AccountBusiness;
import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.Credential;
import io.altar.jseproject.model.TransferObject;
import io.altar.jseproject.repository.AccountRepository;

@Path("account")
public class AccountService extends EntityService<AccountBusiness, AccountRepository, Account> {


	@Inject
	private LoginService login;
	
	@POST
	@Path("/transfer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transfer(Credential credential,TransferObject transfer) {
		if (login.verifyNormal(credential) == true) {
			
			
			return Response.ok(business.moneyTransfer(transfer)).build();
		} else {
			return Response.serverError().entity("Esta operação requer acesso especial").build();
		}
	}
	@POST
	@Path("/deposit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deposit(Credential credential,TransferObject deposit) {
		System.out.println(">>>>>>>>>>>>>esta é a id recebida pelo postman :"+deposit.getAccount1Id());

		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.moneyDeposit(deposit)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@POST
	@Path("/pickup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pickup(Credential credential,TransferObject pickup) {
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.moneyPickup(pickup)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEntityById(@PathParam("id") long id, Credential credential,TransferObject pickup) {
		if (login.verifyEspechial(credential) == true) {
			return Response.ok(business.getEntityById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	
	@GET
	@Path("getallmovementsfromaccount/{id}")
	@Produces("application/json")
	public Response getAllmovementsFromAccount(@PathParam("id") long id, Credential credential,TransferObject pickup) {
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getAllmovementsFromAccount(id)).build();
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

