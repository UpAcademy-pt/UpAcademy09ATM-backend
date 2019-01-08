package io.altar.jseproject.Services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.Business.EntityBusiness;
import io.altar.jseproject.model.BaseEntity;
import io.altar.jseproject.repository.EntityRepository;

public abstract class EntityService<R extends EntityBusiness<S, T>, S extends EntityRepository<T>, T extends BaseEntity> {

	@Context
	private UriInfo context;

	@Inject
	protected R business;

	@Inject
	private LoginService login;

	@GET
	@Path("/health")
	@Produces(MediaType.TEXT_PLAIN)
	public String health() {
		return "ok";
	}

	@POST
	@Path("/newentity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newEntity(T entity, @CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire, @CookieParam("espechial") Cookie espechial) {

		System.out.println(">>>>>>>>>>verificar se é espechial");
		if (login.verifyEspechial(token, expire, espechial) == true) {
			System.out.println(">>>>>>>>>>>>> é espechial");

			return Response.ok(business.newEntity(entity)).build();
		} else {
			System.out.println(">>>>>>>>>>>>> não é espechial");

			
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@POST
	@Path("/changeentity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeEntity(T entity, @CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire, @CookieParam("espechial") Cookie espechial) {
		if (login.verifyEspechial(token, expire, espechial) == true) {
			return Response.ok(business.changeEntity(entity)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEntityById(@PathParam("id") long id, @CookieParam("token") Cookie token,
			@CookieParam("expire") Cookie expire, @CookieParam("espechial") Cookie espechial) {
		if (login.verifyEspechial(token, expire, espechial) == true) {
			return Response.ok(business.getEntityById(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@GET
	@Path("/listentity")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEntity(@CookieParam("token") Cookie token, @CookieParam("expire") Cookie expire, @CookieParam("espechial") Cookie espechial) {
		if (login.verifyEspechial(token, expire, espechial) == true) {
			return Response.ok(business.getAllEntity()).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteEntity(@PathParam("id") long id, @CookieParam("token") Cookie token,
			@CookieParam("expire") Cookie expire, @CookieParam("espechial") Cookie espechial) {
		if (login.verifyEspechial(token, expire, espechial) == true) {
			return Response.ok(business.deleteEntity(id)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

}
