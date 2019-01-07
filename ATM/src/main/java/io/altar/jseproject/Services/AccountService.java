package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.AccountBusiness;
import io.altar.jseproject.model.Account;
import io.altar.jseproject.repository.AccountRepository;

@Path("account")
public class AccountService extends EntityService<AccountBusiness, AccountRepository, Account> {


	@Inject
	private LoginService login;
	
	@POST
	@Path("/transfer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transfer(@FormParam("account1Id") Long account1Id, @FormParam("account2Id") Long account2Id,@FormParam("volume") Double volume,
			@CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire) {
		if (login.verifyNormal(token,expire) == true) {
			return Response.ok(business.moneyTransfer(account1Id,volume,account2Id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@POST
	@Path("/deposit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deposit(@FormParam("accountId") Long accountId, @FormParam("volume") Double volume,
			@CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire) {
		if (login.verifyNormal(token,expire) == true) {
			return Response.ok(business.moneyDeposit(accountId,volume)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
	@POST
	@Path("/pickup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pickup(@FormParam("accountId") Long accountId, @FormParam("volume") Double volume,
			@CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire) {
		if (login.verifyNormal(token,expire) == true) {
			return Response.ok(business.moneyPickup(accountId,volume)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}
}

