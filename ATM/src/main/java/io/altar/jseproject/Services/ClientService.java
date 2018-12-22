package io.altar.jseproject.Services;

import java.io.Serializable;

import javax.ws.rs.Path;

import io.altar.jseproject.Business.ClientBusiness;
import io.altar.jseproject.model.Client;
import io.altar.jseproject.repository.ClientRepository;

@Path("client")
public class ClientService extends EntityService <ClientBusiness,ClientRepository ,Client> implements Serializable {

private static final long serialVersionUID = 1L;

}
