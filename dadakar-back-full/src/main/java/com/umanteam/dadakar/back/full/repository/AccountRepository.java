package com.umanteam.dadakar.back.full.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.enums.Role;

public interface AccountRepository extends MongoRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findByRole(Role role);
	List<Account> findByRoleIsAndRoleIs(Role role1, Role role2);
	List<Account> findByBanned(boolean banned);
	List<Account> findByDeleted(boolean deleted);
	List<Account> findByDeletedAndRole(boolean deleted, Role role);
}
