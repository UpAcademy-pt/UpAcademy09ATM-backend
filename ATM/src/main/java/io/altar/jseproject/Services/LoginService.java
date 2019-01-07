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

//TODO - criar logout para clientes e gestor
//TODO - encriptar password
//TODO - Filipe quer que login retorne na resposta o nome do cliente

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

		if (cli.getPassword().equals(passwordLogin)) {

			if (cli.getEspechial() == false) {

				return loginNormal(cli);

			} else {
				return loginEspechial(cli);
			}

		} else {
			System.out.println(cli.getName() + 2);
			return Response.status(Response.Status.NOT_FOUND).entity("Dados invalidos").build();

		}
	}

	public String generateTokenValue(Long cliId, Date time0) {

		Long tokenValue1 = cliId + time0.getTime();

		Integer tokenValue2 = tokenValue1.hashCode();
		String tokenValueString = tokenValue2.toString();

		return tokenValueString;
	}

	public String generateExpireValue(Date time0, Integer time1) {

		Long expireValue = time0.getTime() + time1;
		String expireValueString = Long.toString(expireValue);
		return expireValueString;

	}

	public String generateEspechialValue(String expireValueString) {
		Integer expireValue0 = "o que faz falta é animar a malta".hashCode() + expireValueString.hashCode();
		Integer expireValue1 = expireValue0.hashCode();
		return expireValue1.toString();
	}

	public Response loginNormal(Client login) {
		Date time0 = new Date();

		Integer time1 = 240;
		Long cliId = login.getId();

		String tokenValue = generateTokenValue(cliId, time0);

		NewCookie tokenCookie = new NewCookie("token", tokenValue, "/", "", NewCookie.DEFAULT_VERSION, null, time1,
				time0, false, false);

		String expireValueString = generateExpireValue(time0, time1);

		NewCookie expireCookie = new NewCookie("expire", expireValueString, "/", "", NewCookie.DEFAULT_VERSION, null,
				time1, time0, false, false);

		Response.ResponseBuilder rb = Response.ok(true);
		Response response = rb.cookie(tokenCookie, expireCookie).build();

		Long token2 = time0.getTime() + time1 + cliId;
		login.setToken(token2.hashCode());
		DBCLIENT.changeEntity(login);

		return response;
	}

	public Response loginEspechial(Client login) {
		Date time0 = new Date();

		Integer time1 = 240;
		Long cliId = login.getId();

		String tokenValue = generateTokenValue(cliId, time0);

		NewCookie tokenCookie = new NewCookie("token", tokenValue, "/", "", NewCookie.DEFAULT_VERSION, null, time1,
				time0, false, false);

		String expireValueString = generateExpireValue(time0, time1);

		NewCookie expireCookie = new NewCookie("expire", expireValueString, "/", "", NewCookie.DEFAULT_VERSION, null,
				time1, time0, false, false);

		String espechialValueString = generateEspechialValue(expireValueString);

		NewCookie espechialCookie = new NewCookie("espechial", espechialValueString, "/", "", NewCookie.DEFAULT_VERSION,
				null, time1, time0, false, false);

		Response.ResponseBuilder rb = Response.ok(true);
		Response response = rb.cookie(tokenCookie, expireCookie, espechialCookie).build();

		Long token2 = time0.getTime() + time1 + cliId;
		login.setToken(token2.hashCode());
		DBCLIENT.changeEntity(login);

		return response;
	}

	public boolean verifyNormal(Cookie token, Cookie expire) {

		if (token == null || expire == null) {

			return false;

		} else {

			Integer tokenValue = Integer.valueOf(token.getValue());
			Long expireValue = Long.valueOf(expire.getValue());
			Long token2 = tokenValue + expireValue;
			Integer token3 = token2.hashCode();

			if (DBCLIENT.findClientByToken(token3) == null) {

				return false;

			} else {

				return true;

			}
		}
	}

	public boolean verifyEspechial(Cookie token, Cookie expire, Cookie espechial) {

		if (token == null || expire == null || espechial == null) {

			return false;

		} else {

			Integer tokenValue = Integer.valueOf(token.getValue());
			Long expireValue = Long.valueOf(expire.getValue());
			Long token2 = tokenValue + expireValue;
			Integer token3 = token2.hashCode();

			if (DBCLIENT.findClientByToken(token3) == null) {

				return false;

			} else {
				String espechialValueString = generateEspechialValue(expire.getValue());

				if (espechialValueString.equals(espechial.getValue())) {
					return true;

				} else {
					return false;
				}

			}
		}
	}
}