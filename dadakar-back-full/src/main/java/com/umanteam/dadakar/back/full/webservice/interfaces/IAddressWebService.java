package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.AddressDTO;

public interface IAddressWebService {
	AddressDTO add(AddressDTO addressDTO);
	AddressDTO update(AddressDTO addressDTO);
	void delete(String id);
	ResponseEntity<List<AddressDTO>> findAll();
	ResponseEntity<AddressDTO> findById(String id);
	ResponseEntity<List<AddressDTO>> findByPostCode(String postCode);
	ResponseEntity<List<AddressDTO>> findByTown(String town);
	ResponseEntity<List<AddressDTO>> findByPostCodeAndTown(String postCode, String Town);
	ResponseEntity<List<AddressDTO>> findByPostCodeAndTownAndDistrict(String postCode, String town, String district);
}
