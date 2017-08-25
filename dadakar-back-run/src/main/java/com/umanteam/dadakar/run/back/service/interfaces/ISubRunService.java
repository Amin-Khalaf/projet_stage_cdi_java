package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.SubRunDTO;

public interface ISubRunService {
	SubRunDTO addOrUpdate(SubRunDTO subRunDTO);
	void delete(String id);
	List<SubRunDTO> findAll();
	SubRunDTO findById(String id);
}
