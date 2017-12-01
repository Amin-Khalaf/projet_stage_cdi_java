package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.RunPriceDTO;

public interface IRunPriceService {
	RunPriceDTO addOrUpdate(RunPriceDTO runPriceDTO);
	void delete(String id);
	List<RunPriceDTO> findAll();
	RunPriceDTO findById(String id);
	RunPriceDTO findByPower(int power);
}
