package io.altar.jseproject.Services;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.Business.ClientBusiness;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.model.Credential;
import io.altar.jseproject.repository.ClientRepository;

@Path("/login")
public class LoginService {

	@Context
	private UriInfo context;

	@Inject
	protected ClientRepository DBCLIENT;

	@Inject
	protected ClientBusiness business;

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
		System.out.println(">>>>>>>>>>>>>>>>>>" + passwordLogin1);
		String passwordLogin2 = passwordLogin1.toString();

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

	@POST
	@Path("/logout/{token}/{expire}/{espechial}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional

	public Response Logout(@PathParam("token") Integer token, @PathParam("expire") Long expire,
			@PathParam("espechial") Integer espechial) {
		Credential credential = new Credential();
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);
		System.out.println(credential);
		Client cli = getClientByToken(credential);
		cli.setToken(null);

		Response response = Response.ok("logout").build();
		return response;

	}

	public Integer generateTokenValue(Long cliId, Date time0) {

		Long tokenValue1 = cliId + time0.getTime();

		Integer tokenValue2 = tokenValue1.hashCode();

		return tokenValue2;
	}

	public Long generateExpireValue(Date time0, Integer time1) {

		Long expireValue = time0.getTime() + time1;

		return expireValue;

	}

	public Integer generateEspechialValue(String expireValueString) {
		Integer expireValue0 = "o que faz falta é animar a malta".hashCode() + expireValueString.hashCode();
		Integer expireValue1 = expireValue0.hashCode();
		return expireValue1;
	}

	public Response loginNormal(Client login) {
		Date time0 = new Date();

		Integer time1 = 240;
		Long cliId = login.getId();

		Integer token = generateTokenValue(cliId, time0);
		Long expire = generateExpireValue(time0, time1);

		Credential credential = new Credential();

		credential.setClient(business.getClientDTO(login));
		credential.setExpire(expire);
		credential.setToken(token);

		Response response = Response.ok(credential).build();

		login.setToken(token);
		DBCLIENT.changeEntity(login);

		return response;
	}

	public Response loginEspechial(Client login) {
		Date time0 = new Date();

		Integer time1 = 90000;
		Long cliId = login.getId();

		Integer token = generateTokenValue(cliId, time0);
		System.out.println(">>>>>>>>valor do token :" + token);

		Long expire = generateExpireValue(time0, time1);
		System.out.println(">>>>>>>>valor do expire:" + expire);

		Integer espechial = generateEspechialValue(expire.toString());
		System.out.println(">>>>>>>>valor do espechial :" + espechial);

		Credential credential = new Credential();

		credential.setClient(business.getClientDTO(login));
		credential.setEspechial(espechial);
		credential.setExpire(expire);
		credential.setToken(token);

		Response response = Response.ok(credential).build();

		login.setToken(token);
		DBCLIENT.changeEntity(login);

		return response;
	}

	public Client getClientByToken(Credential credential) {

		Integer token = Integer.valueOf(credential.getToken());

		System.out.println("valor do token :" + token);

		if (DBCLIENT.findClientByToken(token) == null) {
			Client cli = new Client();
			cli.setName("Malaquias");
			return cli;
		} else {
			Client cli = DBCLIENT.findClientByToken(token);
			return cli;
		}
	}

	public boolean verifyNormal(Credential credential) {

		if (credential.getToken() == null || credential.getExpire() == null) {

			return false;

		} else {
			Client cli = getClientByToken(credential);
			String cliName = cli.getName();

			if (cliName == "Malaquias") {

				return false;

			} else {

				return true;

			}
		}
	}

	public boolean verifyEspechial(Credential credential) {

		if (credential.getToken() == null || credential.getExpire() == null || credential.getEspechial() == null) {
			System.out.println(">>>>>>>credenciais com problemas");
			return false;

		} else {
			System.out.println(">>>>>>>>credencial espechical existe");
			Client cli = getClientByToken(credential);
			System.out.println(">>>>>>>>>cliente encontrado através de cookie");
			String cliName = cli.getName();
			System.out.println(cliName);
			if (cliName.equals("Malaquias") == true) {

				return false;

			} else {
				Integer espechial = credential.getEspechial();
				System.out.println(espechial);
				Integer espechialValue = generateEspechialValue(credential.getExpire().toString());
System.out.println(espechialValue);
				if ( espechial.equals(espechialValue)) {
					System.out.println("é igual");

					return true;

				} else {
					System.out.println("não é igual");
					return false;
				}

			}
		}
	}



}