package com.umanteam.dadakar.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.enums.Role;

public interface IAccountService {
	AccountDTO addOrUpdate(AccountDTO accountDTO);
	void delete(String id);
	List<AccountDTO> findAll();
	AccountDTO findById(String id);
	AccountDTO findByUsername(String username);
	List<AccountDTO> findByRole(Role role);
	List<AccountDTO> findByBanned(boolean banned);
	List<AccountDTO> findByDeleted(boolean deleted);
}
