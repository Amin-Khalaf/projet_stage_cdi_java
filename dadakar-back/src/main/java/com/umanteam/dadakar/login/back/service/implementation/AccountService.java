package com.umanteam.dadakar.login.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.login.back.dto.AccountDTO;
import com.umanteam.dadakar.login.back.entities.Account;
import com.umanteam.dadakar.login.back.enums.Role;
import com.umanteam.dadakar.login.back.repository.AccountRepository;
import com.umanteam.dadakar.login.back.service.interfaces.IAccountService;

@Service("accountService")
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	/* copy from AccountDTO to Account */
	private Account accountDTOToAccount(AccountDTO accountDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDTO, account);
		return account;
	}
	
	/* copy from Account to AccountDTO */
	private AccountDTO accountToAccountDTO(Account account) {
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}

	@Override
	public AccountDTO addOrUpdate(AccountDTO accountDTO) {
		return accountToAccountDTO(accountRepository.save(accountDTOToAccount(accountDTO)));
	}

	@Override
	public void delete(String id) {
		accountRepository.delete(id);
	}

	@Override
	public List<AccountDTO> findAll() {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findAll();
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public AccountDTO findById(String id) {
		Account account = accountRepository.findOne(id);
		if(account != null) return accountToAccountDTO(account);
		return new AccountDTO();
	}

	@Override
	public AccountDTO findByUsername(String username) {
		Account account = accountRepository.findByUsername(username);
		if(account != null) return accountToAccountDTO(account);
		return new AccountDTO();
	}

	@Override
	public List<AccountDTO> findByRole(Role role) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findByRole(role);
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findAdminsAndSuperUsers() {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findByRoleIsOrRoleIs(Role.ADMIN, Role.SUPERUSER);
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}
	
	@Override
	public List<AccountDTO> findByBanned(boolean banned) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findByBanned(banned);
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findByDeleted(boolean deleted) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findByDeleted(deleted);
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findByDeletedAndRole(boolean deleted, Role role) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findByDeletedAndRole(deleted, role);
		if(accounts != null) for(Account account: accounts)  accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

}
