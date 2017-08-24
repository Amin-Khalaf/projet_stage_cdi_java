package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.SubRunDTO;

public interface ISubRunWebService {
	SubRunDTO add(SubRunDTO subRunDTO);
	SubRunDTO update(SubRunDTO subRunDTO);
	void delete(String id);
	ResponseEntity<List<SubRunDTO>> findAll();
	SubRunDTO findById(String id);
}
