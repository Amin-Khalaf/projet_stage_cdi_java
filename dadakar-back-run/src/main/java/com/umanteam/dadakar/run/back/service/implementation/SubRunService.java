package com.umanteam.dadakar.run.back.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.repository.SubRunRepository;
import com.umanteam.dadakar.run.back.service.interfaces.ISubRunService;

@Service("subRunService")
public class SubRunService implements ISubRunService {
	
	@Autowired
	private SubRunRepository subRunRepository;

	@Override
	public SubRunDTO add(SubRunDTO subRunDTO) {
		
		return subRunDTO;
	}

	@Override
	public SubRunDTO update(SubRunDTO subRunDTO) {
		// TODO Auto-generated method stub
		return subRunDTO;
	}

	@Override
	public void delete(String id) {
		subRunRepository.delete(id);
	}

	@Override
	public List<SubRunDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubRunDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
