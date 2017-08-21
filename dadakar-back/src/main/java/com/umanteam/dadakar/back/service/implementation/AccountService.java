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

	@Override
	public AccountDTO add(AccountDTO accountDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDTO, account);
		account = accountRepository.insert(account);
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}

	@Override
	public AccountDTO update(AccountDTO accountDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDTO, account);
		account = accountRepository.save(account);
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
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
		AccountDTO accountDTO = new AccountDTO();
		Account account = accountRepository.findOne(id);
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}

	@Override
	public AccountDTO findByUsername(String username) {
		AccountDTO accountDTO = new AccountDTO();
		Account account = accountRepository.findByUsername(username);
		BeanUtils.copyProperties(account, accountDTO);
		return accountDTO;
	}

	@Override
	public List<AccountDTO> findByRole(Role role) {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByRole(role)) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findBanned() {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByBanned()) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}

	@Override
	public List<AccountDTO> findDeleted() {
		List<AccountDTO> accountDTOs = new ArrayList<>();
		for(Account account: accountRepository.findByDeleted()) {
			AccountDTO accountDTO = new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
			accountDTOs.add(accountDTO);
		}
		return accountDTOs;
	}

}
