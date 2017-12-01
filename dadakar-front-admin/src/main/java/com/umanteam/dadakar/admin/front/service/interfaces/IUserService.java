package com.umanteam.dadakar.admin.front.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.admin.front.dto.Complaint;
import com.umanteam.dadakar.admin.front.dto.AccountUser;

public interface IUserService {
	List<AccountUser> findAll();
	AccountUser findById(String id);
	AccountUser findByAccountId(String accountId);
	AccountUser update(AccountUser accountUser);
	List<Complaint> getComplaint();
}
