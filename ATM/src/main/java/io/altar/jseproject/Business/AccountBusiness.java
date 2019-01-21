package io.altar.jseproject.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.jseproject.model.Account;
import io.altar.jseproject.model.AccountDTO;
import io.altar.jseproject.model.Movement;
import io.altar.jseproject.model.MovementDTO;
import io.altar.jseproject.model.TransferObject;
import io.altar.jseproject.repository.AccountRepository;
import io.altar.jseproject.repository.MovementRepository;

public class AccountBusiness extends EntityBusiness<AccountRepository, Account> {

	@Inject
	private MovementBusiness movement;

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
		String type = "Transfer";

		
		Account account1 = repository.getById(account1Id);
		Account account2 = repository.getById(account2Id);

		double debitBalance = account1.getBalance() - volume;
		double creditBalance = account2.getBalance() + volume;

		if (account1.getBalance() >= volume) {
			Movement movement1 = new Movement(account1, time, description,type ,volume, (Double) 0.0, debitBalance);
			mr.addToDB(movement1);
			Movement movement2 = new Movement(account2, time, description,type,  (Double) 0.0, volume, creditBalance);
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

		Account account1 = repository.getById(account1Id);

		double debitBalance = account1.getBalance() - volume;

		String type = "Pickup";

		if (account1.getBalance() >= volume) {
			Movement movement1 = new Movement(account1, time, pickup.getDescription(),type ,volume, (Double) 0.0, debitBalance);

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
//TODO - é aqui que se devia ter um saldo contabilistico até haver contagem do dinheiro, vamos assumir que se acredita nas boas intenções do accounte

		Long account1Id = deposit.getAccount1Id();
		Double volume = deposit.getVolume();

		Long time = new Date().getTime();

		System.out.println(">>>>>>>>>>>>>>> esta é a id da conta a receber o depósito :" + account1Id);
		Account account1 = repository.getById(account1Id);
System.out.println("conta encontrada :"+account1);
		double creditBalance = account1.getBalance() + volume;
		String type = "Deposit";

		Movement movement1 = new Movement(account1, time, deposit.getDescription(),type , volume, (Double) 0.0, creditBalance);
		mr.addToDB(movement1);

		account1.setBalance(creditBalance);

		return "foi depositado na sua conta o montante entregue";
	}

	public AccountDTO getEntityById(Long id) {
		Account account = repository.getById(id);

		AccountDTO AccountDTO = new AccountDTO();

		AccountDTO.setId(account.getId());
		AccountDTO.setBank(account.getBank());
		AccountDTO.setBalance(account.getBalance());

		return AccountDTO;
	}

	@Transactional
	public List<MovementDTO> getAllmovementsFromAccount(Long id) {
		Account account = repository.getById(id);
		List<Movement> movementList = account.getMovementlist();

		return movement.generateMovementDTOListFromMovementList(movementList);
	}

	public List<AccountDTO> generateAccountDTOListFromAccountList(List<Account> accountList) {
		List<AccountDTO> accountDTOList = new ArrayList<>();
		Iterator<Account> iterator = accountList.iterator();

		while (iterator.hasNext()) {
			Account account = iterator.next();
			AccountDTO AccountDTO = new AccountDTO();

			AccountDTO.setId(account.getId());
			AccountDTO.setBank(account.getBank());
			AccountDTO.setBalance(account.getBalance());

			accountDTOList.add(AccountDTO);
		}
		return accountDTOList;
	}

	@Transactional
	public List<AccountDTO> getAllEntity() {
		List<Account> accountList = repository.getAll();

		List<AccountDTO> accountDTOList = generateAccountDTOListFromAccountList(accountList);

		return accountDTOList;
	}

	@Transactional
	public List<String> getBanksFromClient(Long id) {

		return repository.getBanksFromClient(id);
	}

	@Transactional
	public Map<String, Double> getBanksBalanceFromClient(Long id) {

		Map<String, Double> bankBalanceList = new HashMap<String, Double>();
		
		List<String> bankList = repository.getBanksFromClient(id);

		for (String bank : bankList) {

			Double bankBalance = repository.getBankBalanceFromClient(id, bank);
			bankBalanceList.put(bank, bankBalance);
		}
		System.out.println(bankBalanceList.toString());
		
		return bankBalanceList;
	}
}
