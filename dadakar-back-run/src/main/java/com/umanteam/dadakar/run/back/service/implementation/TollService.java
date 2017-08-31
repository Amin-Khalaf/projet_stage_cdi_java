package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.repository.TollRepository;
import com.umanteam.dadakar.run.back.service.interfaces.ITollService;

@Service("tollService")
public class TollService implements ITollService {
	
	@Autowired
	private TollRepository tollRepository;
	
	/* copy from TollDTO to Toll */
	private Toll tollDTOToToll(TollDTO tollDTO) {
		Toll toll = new Toll();
		BeanUtils.copyProperties(tollDTO, toll);
		return toll;
	}
	
	/* copy from Toll to TollDTO */
	private TollDTO tollToTollDTO(Toll toll) {
		TollDTO tollDTO = new TollDTO();
		BeanUtils.copyProperties(toll, tollDTO);
		return tollDTO;
	}

	@Override
	public TollDTO addOrUpdate(TollDTO tollDTO) {
		return tollToTollDTO(tollRepository.save(tollDTOToToll(tollDTO)));
	}

	@Override
	public void delete(String id) {
		tollRepository.delete(id);
	}

	@Override
	public List<TollDTO> findAll() {
		List<TollDTO> tollDTOs = new ArrayList<>();
		List<Toll> tolls = tollRepository.findAll();
		if(tolls != null) for(Toll toll: tolls) tollDTOs.add(tollToTollDTO(toll));
		return tollDTOs;
	}

	@Override
	public TollDTO findById(String id) {
		Toll toll = tollRepository.findOne(id);
		if(toll != null) return tollToTollDTO(toll);
		return new TollDTO();
	}

}
