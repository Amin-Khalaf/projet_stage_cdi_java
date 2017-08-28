package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.SubRunDTO;

public interface ISubRunWebService {
	SubRunDTO add(SubRunDTO subRunDTO);
	SubRunDTO update(SubRunDTO subRunDTO);
	void delete(String id);
	ResponseEntity<List<SubRunDTO>> findAll();
	ResponseEntity<SubRunDTO> findById(String id);
}
