package io.altar.jseproject.Business;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.model.TransferObject;
import io.altar.jseproject.repository.AccountRepository;
import io.altar.jseproject.repository.MovementRepository;

public class AccountBusiness extends EntityBusiness<AccountRepository, Account> {

	@Inject
	protected AccountRepository ar;

	@Inject
	protected MovementRepository mr;

	@Transactional
	public String moneyTransfer(TransferObject transfer) {
		// transferir dinheiro de uma conta para outra sabendo o id das duas contas e o
		// montante a transferir

		Long time = new Date().getTime();
		Long account1Id = transfer.getAccount1Id();
		Long account2Id = transfer.getAccount2Id();
		Double volume = transfer.getVolume();

		String description = "Transference from Account nº" + account1Id + " to Account nº" + account2Id;

		Account account1 = ar.getById(account1Id);
		Account account2 = ar.getById(account2Id);

		double debitBalance = account1.getBalance() - volume;
		double creditBalance = account2.getBalance() + volume;

		if (account1.getBalance() >= volume) {
			Movement movement1 = new Movement(account1, time, description, volume, (Double) 0.0, debitBalance);
			mr.addToDB(movement1);
			Movement movement2 = new Movement(account2, time, description, (Double) 0.0, volume, creditBalance);
			mr.addToDB(movement2);

			account1.setBalance(debitBalance);
			account2.setBalance(creditBalance);
			return "Transferência realizada com sucesso";
		} else {
			return "A conta não apresenta saldo suficiente para proceder à transação";
		}
	}

	@Transactional
	public String moneyPickup(TransferObject pickup) {
		// levantmento de dinheiro
		Long account1Id = pickup.getAccount1Id();
		Double volume = pickup.getVolume();

		Long time = new Date().getTime();

		String description = "Money pickup from Account nº" + account1Id;

		Account account1 = ar.getById(account1Id);

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
	public String moneyDeposit(TransferObject deposit) {
		// depositar dinheiro
//TODO - é aqui que se devia ter um saldo contabilistico até haver contagem do dinheiro, vamos assumir que se acredita nas boas intenções do cliente

		Long account1Id = deposit.getAccount1Id();
		Double volume = deposit.getVolume();

		Long time = new Date().getTime();

		String description = "Money deposit to Account nº" + account1Id;
		System.out.println(">>>>>>>>>>>>>>> esta é a id da conta a receber o depósito :" + account1Id);
		Account account1 = ar.getById(account1Id);

		double creditBalance = account1.getBalance() + volume;

		Movement movement1 = new Movement(account1, time, description, volume, (Double) 0.0, creditBalance);
		mr.addToDB(movement1);

		account1.setBalance(creditBalance);

		return "foi depositado na sua conta o montante entregue";
	}
}
