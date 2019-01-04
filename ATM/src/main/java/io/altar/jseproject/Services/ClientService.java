package io.altar.jseproject.Services;

//import java.util.Date;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Cookie;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.NewCookie;
//import javax.ws.rs.core.Response;

import io.altar.jseproject.Business.ClientBusiness;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.ClientRepository;

@Path("client")
public class ClientService extends EntityService<ClientBusiness, ClientRepository, Client> {
//	@Inject
//	protected ClientRepository DBCLIENT;
//
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response Login(@PathParam("emailLogin") String emailLogin,
//			@PathParam("passwordLogin") String passwordLogin) {
//
//		Client cli = DBCLIENT.findClientByEmail(emailLogin);
//		if (cli.getPassword() == passwordLogin) {
////				https://stackoverflow.com/questions/4620172/expires-string-in-cookie-header
//
//			Date time0 = new Date();
//			Long time1 = (time0.getTime() + 1800);
//			NewCookie cookie1 = new NewCookie("expires", time1.toString());
//
//			Long cliId = cli.getId();
//			Integer token = cliId.hashCode();
//			NewCookie cookie2 = new NewCookie("token", token.toString());
//
//			Response.ResponseBuilder rb = Response.ok(true);
//			Response response = rb.cookie(cookie1, cookie2).build();
//
//			Long token2 = time1 + token;
//			cli.setToken(token2.hashCode());
//			DBCLIENT.changeEntity(cli);
//
//			return response;
//
//		} else {
//
//			return Response.status(Response.Status.NOT_FOUND).entity("Dados invalidos").build();
//
//		}
//	}
//
//	public boolean verify(Cookie tokenCheck, Cookie expiresCheck) {
//
//		if (tokenCheck == null || expiresCheck == null) {
//
//			return false;
//
//		} else {
//
//			String token = tokenCheck.getValue();
//			Integer tokenInt = Integer.valueOf(token);
//
//			String expires = expiresCheck.getValue();
//			Long expiresLong = Long.valueOf(expires);
//
//			Long token2 = tokenInt + expiresLong;
//			Integer token3 = token2.hashCode();
//
//			if (DBCLIENT.findClientByToken(token3.toString()) == null) {
//
//				return false;
//
//			} else {
//
//				return true;
//
//			}
//		}
//	}
}