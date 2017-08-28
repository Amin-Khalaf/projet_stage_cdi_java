package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.RunPriceDTO;

public interface IRunPriceWebService {
	RunPriceDTO add(RunPriceDTO runPriceDTO);
	RunPriceDTO update(RunPriceDTO runPriceDTO);
	void delete(String id);
	ResponseEntity<List<RunPriceDTO>> findAll();
	RunPriceDTO findById(String id);
	RunPriceDTO findByPower(int power);
}
