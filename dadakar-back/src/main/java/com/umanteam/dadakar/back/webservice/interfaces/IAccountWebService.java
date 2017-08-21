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
	ResponseEntity<List<AccountDTO>> findBanned();
	ResponseEntity<List<AccountDTO>> findDeleted();
}
