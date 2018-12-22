package io.altar.jseproject.Business;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.repository.AccountRepository;
import io.altar.jseproject.repository.MovementRepository;

public class AccountBusiness extends EntityBusiness<AccountRepository, Account> {

	@Inject
	protected AccountRepository ar;

	@Inject
	protected MovementRepository mr;

	@Transactional
	public String moneyTransfer(Long account1id, Double volume, Long account2id) {
		// transferir dinheiro de uma conta para outra sabendo o id das duas contas e o
		// montante a transferir

		Long time = new Date().getTime();

		String description = "Transference from Account nº" + account1id + " to Account nº" + account2id;

		Account account1 = ar.getById(account1id);
		Account account2 = ar.getById(account2id);

		double debitBalance = account1.getBalance() - volume;
		double creditBalance = account1.getBalance() + volume;

		if (account1.getBalance() >= volume) {
			Movement movement1 = new Movement(account1, time, description, volume, (Double) 0.0, debitBalance);
			mr.addToDB(movement1);
			Movement movement2 = new Movement(account2, time, description, (Double) 0.0, volume, creditBalance);
			mr.addToDB(movement2);

			account1.setBalance(debitBalance);
			account2.setBalance(creditBalance);
			return "uma mensgagem qualquer";
		} else {
			return "A conta não apresenta saldo suficiente para proceder à transação";
		}
	}

	@Transactional
	public String moneyTransfer(Long account1id, Double volume) {
		// levantmento de dnheiro

		Long time = new Date().getTime();

		String description = "Money pickup from Account nº" + account1id;

		Account account1 = ar.getById(account1id);

		double debitBalance = account1.getBalance() - volume;

		if (account1.getBalance() >= volume) {
			Movement movement1 = new Movement(account1, time, description, volume, (Double) 0.0, debitBalance);
			mr.addToDB(movement1);

			account1.setBalance(debitBalance);

			return "uma mensgagem qualquer";
		} else {
			return "A conta não apresenta saldo suficiente para proceder ao levantamento desejado";
		}
	}

	@Transactional
	public String moneyDeposit(Long account1id, Double volume) {
		// depositar dinheiro
//TODO - é aqui que se devia ter um saldo contabilistico até haver contagem do dinheiro, vamos assumir que se acredita nas boas intenções do cliente
		
		Long time = new Date().getTime();

		String description = "Money deposit to Account nº" + account1id;

		Account account1 = ar.getById(account1id);

		double creditBalance = account1.getBalance() + volume;

			Movement movement1 = new Movement(account1, time, description, volume, (Double) 0.0, creditBalance);
			mr.addToDB(movement1);

			account1.setBalance(creditBalance);

			return "foi depositado na sua conta o montante entregue";
	}
}
