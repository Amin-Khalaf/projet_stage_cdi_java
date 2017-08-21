package com.umanteam.dadakar.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.enums.Role;

public interface AccountRepository extends MongoRepository<Account, String> {
	Account findByUsername(String username);
	List<Account> findByRole(Role role);
	List<Account> findByBanned();
	List<Account> findByDeleted();
}
