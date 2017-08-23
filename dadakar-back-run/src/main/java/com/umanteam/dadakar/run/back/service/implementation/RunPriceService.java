package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.umanteam.dadakar.run.back.dto.RunPriceDTO;
import com.umanteam.dadakar.run.back.entities.RunPrice;
import com.umanteam.dadakar.run.back.repository.RunPriceRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunPriceService;

public class RunPriceService implements IRunPriceService {
	
	@Autowired
	private RunPriceRepository runPriceRepository;

	@Override
	public RunPriceDTO add(RunPriceDTO runPriceDTO) {
		RunPrice runPrice = new RunPrice();
		BeanUtils.copyProperties(runPriceDTO, runPrice);
		runPrice = runPriceRepository.insert(runPrice);
		BeanUtils.copyProperties(runPrice, runPriceDTO);
		return runPriceDTO;
	}

	@Override
	public RunPriceDTO update(RunPriceDTO runPriceDTO) {
		RunPrice runPrice = new RunPrice();
		BeanUtils.copyProperties(runPriceDTO, runPrice);
		runPrice = runPriceRepository.save(runPrice);
		BeanUtils.copyProperties(runPrice, runPriceDTO);
		return runPriceDTO;
	}

	@Override
	public void delete(String id) {
		runPriceRepository.delete(id);
	}

	@Override
	public List<RunPriceDTO> findAll() {
		List<RunPriceDTO> runPriceDTOs = new ArrayList<>();
		for(RunPrice runPrice: runPriceRepository.findAll()) {
			RunPriceDTO runPriceDTO = new RunPriceDTO();
			BeanUtils.copyProperties(runPrice, runPriceDTO);
			runPriceDTOs.add(runPriceDTO);
		}
		return runPriceDTOs;
	}

	@Override
	public RunPriceDTO findById(String id) {
		RunPriceDTO runPriceDTO = new RunPriceDTO();
		BeanUtils.copyProperties(runPriceRepository.findOne(id), runPriceDTO);
		return runPriceDTO;
	}

	@Override
	public RunPriceDTO findByPower(int power) {
		RunPriceDTO runPriceDTO = new RunPriceDTO();
		BeanUtils.copyProperties(runPriceRepository.findByPower(power), runPriceDTO);
		return runPriceDTO;
	}

}
