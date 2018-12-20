package io.altar.jseproject.Services;

import java.io.Serializable;
//import java.util.List;

import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.Business.ClientBusiness;
//import io.altar.jseproject.model.Client;

@Path("/atm")
public class ClientServices implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected ClientBusiness cb;

	@Context
	private UriInfo context;

	@GET
	@Path("/health")
	@Produces(MediaType.TEXT_PLAIN)
	public String health() {
		return "ok";
	}

//	@GET
//	@Path("/{id}")
//	@Produces("application/json")
//	public Client getBalanceById(@PathParam("id") long id) {
//
//		return (Client) cb.findById(id);
//	}
//
//	@GET
//	@Path("/")
//	@Produces(MediaType.APPLICATION_JSON)
//
//	public List<Client> listMovimentos() {
//
//		return cb.getAll();
//	}

}
