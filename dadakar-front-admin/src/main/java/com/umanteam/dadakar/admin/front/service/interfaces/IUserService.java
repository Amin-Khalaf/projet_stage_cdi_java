package com.umanteam.dadakar.admin.front.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.admin.front.dto.User;

public interface IUserService {
	List<User> findAll();
	User findById(String id);
	User update(User user);
}
