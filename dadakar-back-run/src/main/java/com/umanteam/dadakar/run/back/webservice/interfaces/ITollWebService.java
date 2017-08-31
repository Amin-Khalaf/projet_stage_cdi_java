package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.TollDTO;

public interface ITollWebService {
	TollDTO add(TollDTO tollDTO);
	TollDTO update(TollDTO tollDTO);
	void delete(String id);
	ResponseEntity<List<TollDTO>> findAll();
	ResponseEntity<TollDTO> findById(String id);
}
