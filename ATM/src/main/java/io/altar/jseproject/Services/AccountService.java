package io.altar.jseproject.Services;

import javax.ws.rs.Path;

import io.altar.jseproject.Business.AccountBusiness;
import io.altar.jseproject.model.Account;
import io.altar.jseproject.repository.AccountRepository;

@Path("account")
public class AccountService extends EntityService<AccountBusiness, AccountRepository, Account> {

}
