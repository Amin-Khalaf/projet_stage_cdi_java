package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.AddressDTO;

public interface IAddressWebService {
	AddressDTO add(AddressDTO addressDTO);
	AddressDTO update(AddressDTO addressDTO);
	void delete(String id);
	ResponseEntity<List<AddressDTO>> findAll();
	ResponseEntity<AddressDTO> findById(String id);
	ResponseEntity<List<AddressDTO>> findByTown(String town);
	ResponseEntity<AddressDTO> findByTownAndDistrict(String town, String district);
}
