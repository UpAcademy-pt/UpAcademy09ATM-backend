package io.altar.jseproject.Services;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
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
	@Path("manager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Client createManager() {

		Client manager = new Client();

		manager.setEmail("manager@mail.com");
		manager.setName("Manager");
		manager.setPassword("-146771471");
		manager.setTel((long) 111222333);
		manager.setEspechial(true);

		System.out.println("antes");
		System.out.println(manager.getEspechial());
		System.out.println("depois");
		return DBCLIENT.addToDB(manager);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response Login(Client login) {

		String emailLogin = login.getEmail();
		String passwordLogin0 = login.getPassword();
		Integer passwordLogin1 = passwordLogin0.hashCode();
		System.out.println(">>>>>>>>>>>>>>>>>>"+passwordLogin1);
		String passwordLogin2=passwordLogin1.toString();

		Client cli = DBCLIENT.findClientByEmail(emailLogin);
System.out.println(">>>>>>>>login é especial?");
		if (cli.getPassword().equals(passwordLogin2.toString())) {

			if (cli.getEspechial() == false) {
				System.out.println(">>>>>>>>login é normal");
				return loginNormal(cli);

			} else {
				System.out.println(">>>>>>>>login especial");
				return loginEspechial(cli);
			}

		} else {
			System.out.println(cli.getName() + 2);
			return Response.status(Response.Status.NOT_FOUND).entity("Dados invalidos").build();

		}
	}

	public Integer generateTokenValue(Long cliId, Date time0) {

		Long tokenValue1 = cliId + time0.getTime();

		Integer tokenValue2 = tokenValue1.hashCode();

		return tokenValue2;
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

		Integer tokenValue = generateTokenValue(cliId, time0);

		NewCookie tokenCookie = new NewCookie("token", tokenValue.toString(), "/", "", NewCookie.DEFAULT_VERSION, null, time1,
				time0, false, false);

		String expireValueString = generateExpireValue(time0, time1);

		NewCookie expireCookie = new NewCookie("expire", expireValueString, "/", "", NewCookie.DEFAULT_VERSION, null,
				time1, time0, false, false);

		Response.ResponseBuilder rb = Response.ok(login);
		Response response = rb.cookie(tokenCookie, expireCookie).build();


		login.setToken(tokenValue);
		DBCLIENT.changeEntity(login);

		return response;
	}

	public Response loginEspechial(Client login) {
		Date time0 = new Date();

		Integer time1 = 90000;
		Long cliId = login.getId();

		Integer tokenValue = generateTokenValue(cliId, time0);
		System.out.println(">>>>>>>>valor do token :"+tokenValue);
		NewCookie tokenCookie = new NewCookie("token", tokenValue.toString(), "/", "", NewCookie.DEFAULT_VERSION, null, time1,
				time0, false, false);

		String expireValueString = generateExpireValue(time0, time1);
		System.out.println(">>>>>>>>valor do expire:"+expireValueString);

		NewCookie expireCookie = new NewCookie("expire", expireValueString, "/", "", NewCookie.DEFAULT_VERSION, null,
				time1, time0, false, false);

		String espechialValueString = generateEspechialValue(expireValueString);

		NewCookie espechialCookie = new NewCookie("espechial", espechialValueString, "/", "", NewCookie.DEFAULT_VERSION,
				null, time1, time0, false, false);
		System.out.println(">>>>>>>>valor do espechial :"+espechialCookie);

		Response.ResponseBuilder rb = Response.ok(true);
		Response response = rb.cookie(tokenCookie, expireCookie, espechialCookie).build();

		login.setToken(tokenValue);
		DBCLIENT.changeEntity(login);

		return response;
	}

	public Client getClientByCookie(Cookie token, Cookie expire) {
		
		Integer tokenValue = Integer.valueOf(token.getValue());
		
System.out.println("valor do token :"+tokenValue);


		if (DBCLIENT.findClientByToken(tokenValue) == null) {
			Client cli = new Client();
			cli.setName("Malaquias");
			return cli;
		} else {
			Client cli = DBCLIENT.findClientByToken(tokenValue);
			return cli;
		}
	}

	public boolean verifyNormal(Cookie token, Cookie expire) {

		if (token == null || expire == null) {

			return false;

		} else {
			Client cli = getClientByCookie(token, expire);
			String cliName = cli.getName();

			if (cliName == "Malaquias") {

				return false;

			} else {

				return true;

			}
		}
	}

	public boolean verifyEspechial(Cookie token, Cookie expire, Cookie espechial) {

		if (token == null || expire == null || espechial == null) {
System.out.println(">>>>>>>cookies com problemas");
			return false;

		} else {
System.out.println(">>>>>>>>Cookie espechical existe");
			Client cli = getClientByCookie(token, expire);
			System.out.println(">>>>>>>>>cliente encontrado através de cookie");
			String cliName = cli.getName();

			if (cliName.equals("Malaquias") == true) {

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

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response Logout(@CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire,
			@CookieParam("espechial") Cookie espechial) {
		Client cli = getClientByCookie(token, expire);
		cli.setToken(null);

		NewCookie tokenCookie = new NewCookie("token", null, "/", "", NewCookie.DEFAULT_VERSION, null, 0, new Date(), false,
				false);

		NewCookie expireCookie = new NewCookie("expire", null, "/", "", NewCookie.DEFAULT_VERSION, null, 0, new Date(),
				false, false);

		NewCookie espechialCookie = new NewCookie("espechial", null, "/", "", NewCookie.DEFAULT_VERSION, null, 0, new Date(),
				false, false);

		Response.ResponseBuilder rb = Response.ok("logout");
		Response response = rb.cookie(tokenCookie, expireCookie, espechialCookie).build();

		return response;

	}

}