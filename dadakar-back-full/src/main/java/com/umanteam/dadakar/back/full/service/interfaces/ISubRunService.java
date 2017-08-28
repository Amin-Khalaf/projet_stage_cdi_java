package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.SubRunDTO;

public interface ISubRunService {
	SubRunDTO addOrUpdate(SubRunDTO subRunDTO);
	void delete(String id);
	List<SubRunDTO> findAll();
	SubRunDTO findById(String id);
}
