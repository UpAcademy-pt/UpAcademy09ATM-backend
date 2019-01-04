package io.altar.jseproject.Services;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.ClientRepository;

@Path("/login")
public class LoginService {

	@Context
	private UriInfo context;

	@Inject
	protected ClientRepository DBCLIENT;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response Login(Client login) {

		String emailLogin = login.getEmail();
		String passwordLogin = login.getPassword();

		Client cli = DBCLIENT.findClientByEmail(emailLogin);

		System.out.println(passwordLogin + 1);
		System.out.println(cli.getPassword() + 2);

		if (cli.getPassword().equals(passwordLogin)) {
			Date time0 = new Date();
			Integer time1 = 240;
			Long cliId = cli.getId();
			Integer tokenValue = cliId.hashCode();
			
			Long expireValue=time0.getTime()+time1;
			String expireValueString= Long.toString(expireValue);
			
			NewCookie tokenCookie = new NewCookie("token", tokenValue.toString(), "/", "", NewCookie.DEFAULT_VERSION, null, time1, time0, false, false);
			NewCookie expireCookie = new NewCookie("expire",expireValueString, "/", "", NewCookie.DEFAULT_VERSION, null, time1, time0, false, false);

			Response.ResponseBuilder rb = Response.ok(true);
			Response response = rb.cookie(tokenCookie,expireCookie).build();

			Long token2 = expireValue + tokenValue;
			cli.setToken(token2.hashCode());
			DBCLIENT.changeEntity(cli);

			return response;

		} else {
			System.out.println(cli.getName() + 2);
			return Response.status(Response.Status.NOT_FOUND).entity("Dados invalidos").build();

		}
	}

	public boolean verify(Cookie cookie,Cookie expire) {

		if (cookie == null||expire==null) {

			return false;

		} else {

//		System.out.println("aqui tá ele"+cookie);

		Integer tokenValue = Integer.valueOf(cookie.getValue());
Long expireValue=Long.valueOf(expire.getValue());
Long token2=tokenValue+expireValue;
Integer token3=token2.hashCode();

//System.out.println(tytoken3);
//Long expires =cookie.getExpiry().getTime();
//			Long token3 = token2 + expires;
//			Integer token4 = token3.hashCode();

			
			if (DBCLIENT.findClientByToken(token3) == null) {
	
				return false;

			} else {

			return true;

		}
	}
	}
}