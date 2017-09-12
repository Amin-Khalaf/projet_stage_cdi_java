package com.umanteam.dadakar.login.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.login.back.entities.Account;
import com.umanteam.dadakar.login.back.enums.Role;

public interface AccountRepository extends MongoRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findByRole(Role role);
	List<Account> findByRoleIsOrRoleIs(Role role1, Role role2);
	List<Account> findByBanned(boolean banned);
	List<Account> findByDeleted(boolean deleted);
	List<Account> findByDeletedAndRole(boolean deleted, Role role);
}
