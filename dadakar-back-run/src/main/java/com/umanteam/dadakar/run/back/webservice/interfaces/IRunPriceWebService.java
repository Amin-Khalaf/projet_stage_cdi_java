package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.RunPriceDTO;

public interface IRunPriceWebService {
	RunPriceDTO add(RunPriceDTO runPriceDTO);
	RunPriceDTO update(RunPriceDTO runPriceDTO);
	void delete(String id);
	ResponseEntity<List<RunPriceDTO>> findAll();
	ResponseEntity<RunPriceDTO> findById(String id);
	ResponseEntity<RunPriceDTO> findByPower(int power);
}
