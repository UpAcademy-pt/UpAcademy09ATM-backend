package io.altar.jseproject.Services;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.ClientRepository;

@Path("login")
public class LoginService {

	@Inject
	protected ClientRepository DBCLIENT;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response Login(String emailLogin, String passwordLogin) {

		Client cli = DBCLIENT.findClientByEmail(emailLogin);
		if (cli.getPassword() == passwordLogin) {
//				https://stackoverflow.com/questions/4620172/expires-string-in-cookie-header

			Date time0 = new Date();
			Long time1 = time0.getTime();

			Long cliId = cli.getId();
			Long number = time1 + cliId;
			Integer token = number.hashCode();

			cli.setToken(token);
			DBCLIENT.changeEntity(cli);

			NewCookie cookie1 = new NewCookie("token", token.toString());

			time0.setTime(time0.getTime() + 1800);

			NewCookie cookie2 = new NewCookie("expires",time0);

			Response.ResponseBuilder rb = Response.ok(true);
			Response response = rb.cookie(cookie1, cookie2).build();
			return response;
		} else {

			return Response.status(Response.Status.NOT_FOUND).entity("Dados invalidos").build();

		}
	}

	public Response verify(@CookieParam("token") Cookie cookie) {
		if (cookie == null) {
			return Response.serverError().entity("ERROR").build();
		} else {

			return Response.ok(cookie.getValue()).build();
		}
	}

}
//public Response changeEntity(@CookieParam("name") Cookie cookie, T entity) {
//    if (cookie == null) {
//        return Response.serverError().entity("ERROR").build();
//    } else {
//        return Response.ok(business.changeEntity(entity)).build();
//    }
//}
//@GET
//@Path("/foo")
//@Produces(MediaType.TEXT_PLAIN)
//public Response foo(@CookieParam("name") Cookie cookie) {
//    if (cookie == null) {
//        return Response.serverError().entity("ERROR").build();
//    } else {
//        return Response.ok(cookie.getValue()).build();
//    }