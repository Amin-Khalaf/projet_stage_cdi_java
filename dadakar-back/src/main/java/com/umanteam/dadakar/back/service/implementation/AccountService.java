package com.umanteam.dadakar.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.back.repository.AccountRepository;
import com.umanteam.dadakar.back.service.interfaces.IAccountService;

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
		for(Account account: accountRepository.findAll()) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}

	@Override
	public AccountDTO findById(String id) {
		return accountToAccountDTO(accountRepository.findOne(id));
	}

	@Override
	public AccountDTO findByUsername(String username) {
		return accountToAccountDTO(accountRepository.findByUsername(username));
	}

	@Override
	public List<AccountDTO> findByRole(Role role) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByRole(role)) accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findByBanned(boolean banned) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByBanned(banned)) accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findByDeleted(boolean deleted) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByDeleted(deleted)) accountDTOs.add(accountToAccountDTO(account));
		return accountDTOs;
	}

}
