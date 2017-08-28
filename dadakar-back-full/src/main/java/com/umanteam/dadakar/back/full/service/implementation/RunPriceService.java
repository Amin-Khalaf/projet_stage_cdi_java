package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.RunPriceDTO;
import com.umanteam.dadakar.back.full.entities.RunPrice;
import com.umanteam.dadakar.back.full.repository.RunPriceRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IRunPriceService;

@Service("runPriceService")
public class RunPriceService implements IRunPriceService {
	
	@Autowired
	private RunPriceRepository runPriceRepository;
	
	/* copy from RunPriceDTO to RunPrice */
	private RunPrice runPriceDTOToRunPrice(RunPriceDTO runPriceDTO) {
		RunPrice runPrice = new RunPrice();
		BeanUtils.copyProperties(runPriceDTO, runPrice);
		return runPrice;
	}
	
	/* copy from RunPrice to RunPriceDTO */
	private RunPriceDTO runPriceToRunPriceDTO(RunPrice runPrice) {
		RunPriceDTO runPriceDTO = new RunPriceDTO();
		BeanUtils.copyProperties(runPrice, runPriceDTO);
		return runPriceDTO;
	}

	@Override
	public RunPriceDTO addOrUpdate(RunPriceDTO runPriceDTO) {
		return runPriceToRunPriceDTO(runPriceRepository.save(runPriceDTOToRunPrice(runPriceDTO)));
	}

	@Override
	public void delete(String id) {
		runPriceRepository.delete(id);
	}

	@Override
	public List<RunPriceDTO> findAll() {
		List<RunPriceDTO> runPriceDTOs = new ArrayList<>();
		List<RunPrice> runPrices = runPriceRepository.findAll();
		if(runPrices != null) for(RunPrice runPrice: runPrices) runPriceDTOs.add(runPriceToRunPriceDTO(runPrice));
		return runPriceDTOs;
	}

	@Override
	public RunPriceDTO findById(String id) {
		RunPrice runPrice = runPriceRepository.findOne(id);
		if(runPrice != null) return runPriceToRunPriceDTO(runPrice);
		else return new RunPriceDTO();
	}

	@Override
	public RunPriceDTO findByPower(int power) {
		RunPrice runPrice = runPriceRepository.findByPower(power);
		if(runPrice != null) return runPriceToRunPriceDTO(runPrice);
		else return new RunPriceDTO();
	}

}
