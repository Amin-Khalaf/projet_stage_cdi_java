package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.UserDTO;

public interface IUserService {
	UserDTO addOrUpdate(UserDTO userDTO);
	void delete(String id);
	List<UserDTO> findAll();
	UserDTO findById(String id);
	List<UserDTO> findByLastName(String lastName);
	UserDTO findByAccountId(String accountId);
}
