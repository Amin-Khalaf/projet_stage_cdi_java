package com.umanteam.dadakar.admin.front.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.admin.front.dto.Account;

public interface IAccountService {
	Account add(Account Account);
	Account update(Account Account);
	void delete(String id);
	List<Account> findAll();
	Account findById(String id);
	Account findByUsername(String username);
	List<Account> findUsers();
	List<Account> findAdmins();
	List<Account> findSuperUsers();
	List<Account> findAdminsAndSuperUsers();
	List<Account> findBanned();
	List<Account> findNotBanned();
	List<Account> findDeleted();
	List<Account> findUsersDeleted();
	List<Account> findAdminsDeleted();
	List<Account> findNotDeleted();
	List<Account> findUsersNotDeleted();
	List<Account> findAdminsNotDeleted();

}
