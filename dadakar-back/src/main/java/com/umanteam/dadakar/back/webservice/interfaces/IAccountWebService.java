package com.umanteam.dadakar.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.AccountDTO;

public interface IAccountWebService {
	AccountDTO add(AccountDTO accountDTO);
	AccountDTO update(AccountDTO accountDTO);
	void delete(String id);
	ResponseEntity<List<AccountDTO>> findAll();
	AccountDTO findById(String id);
	AccountDTO findByUsername(String username);
	ResponseEntity<List<AccountDTO>> findUsers();
	ResponseEntity<List<AccountDTO>> findAdmins();
	ResponseEntity<List<AccountDTO>> findSuperUsers();
	ResponseEntity<List<AccountDTO>> findAdminsAndSuperUsers();
	ResponseEntity<List<AccountDTO>> findBanned();
	ResponseEntity<List<AccountDTO>> findNotBanned();
	ResponseEntity<List<AccountDTO>> findDeleted();
	ResponseEntity<List<AccountDTO>> findUsersDeleted();
	ResponseEntity<List<AccountDTO>> findAdminsDeleted();
	ResponseEntity<List<AccountDTO>> findNotDeleted();
	ResponseEntity<List<AccountDTO>> findUsersNotDeleted();
	ResponseEntity<List<AccountDTO>> findAdminsNotDeleted();
}
