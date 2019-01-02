package io.altar.jseproject.Services;

//import java.util.List;
import java.util.List;

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
	public Response newEntity(T entity, @CookieParam("token") Cookie tokenCheck, @CookieParam("expires") Cookie expiresCheck) {
		if (login.verify(tokenCheck, expiresCheck) == true) {
			return Response.ok(business.newEntity(entity)).build();
		} else {
			return Response.serverError().entity("goToLogin").build();
		}
	}

	@POST
	@Path("/changeentity")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T changeEntity(T entity) {

		return business.changeEntity(entity);

	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public T getEntityById(@PathParam("id") long id) {

		return business.getEntityById(id);
	}

	@GET
	@Path("/listentity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<T> listEntity() {

		return business.getAllEntity();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEntity(@PathParam("id") long id) {

		return business.deleteEntity(id);
	}

}
