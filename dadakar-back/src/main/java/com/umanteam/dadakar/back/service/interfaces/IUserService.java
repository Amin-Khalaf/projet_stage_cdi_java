package com.umanteam.dadakar.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.UserDTO;

public interface IUserService {
	UserDTO add(UserDTO userDTO);
	UserDTO update(UserDTO userDTO);
	void delete(String id);
	List<UserDTO> findAll();
	UserDTO findById(String id);
	List<UserDTO> findByLastName(String lastName);
	UserDTO findByAccount(AccountDTO accountDTO);
	UserDTO findByAccountUsername(String username);
}
