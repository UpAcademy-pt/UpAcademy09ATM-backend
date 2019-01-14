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
	@Path("/transfer/{token}/{expire}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transfer(TransferObject transfer,@PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			
			
			return Response.ok(business.moneyTransfer(transfer)).build();
		} else {
			return Response.serverError().entity("Esta operação requer acesso especial").build();
		}
	}
	@POST
	@Path("/deposit/{token}/{expire}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deposit(TransferObject deposit,@PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		System.out.println(">>>>>>>>>>>>>esta é a id recebida pelo postman :"+deposit.getAccount1Id());

		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.moneyDeposit(deposit)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@POST
	@Path("/pickup/{token}/{expire}/{espechial}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pickup(TransferObject pickup,@PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.moneyPickup(pickup)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
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
	@Path("getallmovementsfromaccount/{id}/{token}/{expire}")
	@Produces("application/json")
	public Response getAllmovementsFromAccount(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getAllmovementsFromAccount(id)).build();
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
	@Path("getbanksfromclient/{id}/{token}/{expire}")
	@Produces("application/json")
	public Response getBanksFromClient(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getBanksFromClient(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@GET
	@Path("getbanksbalancefromclient/{id}/{token}/{expire}")
	@Produces("application/json")
	public Response getBanksBalanceFromClient(@PathParam("id") long id, @PathParam("token") Integer token, @PathParam("expire") Long expire) {
		Credential credential = new Credential();
		credential.setExpire(expire);
		credential.setToken(token);
		if (login.verifyNormal(credential) == true) {
			return Response.ok(business.getBanksBalanceFromClient(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
}

