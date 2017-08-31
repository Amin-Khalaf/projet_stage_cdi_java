package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.TollDTO;

public interface ITollService {
	TollDTO addOrUpdate(TollDTO tollDTO);
	void delete(String id);
	List<TollDTO> findAll();
	TollDTO findById(String id);
}
