package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.RunPriceDTO;
import com.umanteam.dadakar.run.back.entities.RunPrice;
import com.umanteam.dadakar.run.back.repository.RunPriceRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunPriceService;

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
	public RunPriceDTO add(RunPriceDTO runPriceDTO) {
		return runPriceToRunPriceDTO(runPriceRepository.insert(runPriceDTOToRunPrice(runPriceDTO)));
	}

	@Override
	public RunPriceDTO update(RunPriceDTO runPriceDTO) {
		return runPriceToRunPriceDTO(runPriceRepository.save(runPriceDTOToRunPrice(runPriceDTO)));
	}

	@Override
	public void delete(String id) {
		runPriceRepository.delete(id);
	}

	@Override
	public List<RunPriceDTO> findAll() {
		List<RunPriceDTO> runPriceDTOs = new ArrayList<>();
		for(RunPrice runPrice: runPriceRepository.findAll()) runPriceDTOs.add(runPriceToRunPriceDTO(runPrice));
		return runPriceDTOs;
	}

	@Override
	public RunPriceDTO findById(String id) {
		return runPriceToRunPriceDTO(runPriceRepository.findOne(id));
	}

	@Override
	public RunPriceDTO findByPower(int power) {
		return runPriceToRunPriceDTO(runPriceRepository.findByPower(power));
	}

}
