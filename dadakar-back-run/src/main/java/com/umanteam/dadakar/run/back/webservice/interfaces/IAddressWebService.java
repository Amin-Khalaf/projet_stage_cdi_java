package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.AddressDTO;

public interface IAddressWebService {
	AddressDTO add(AddressDTO addressDTO);
	AddressDTO update(AddressDTO addressDTO);
	void delete(String id);
	ResponseEntity<List<AddressDTO>> findAll();
	AddressDTO findById(String id);
	ResponseEntity<List<AddressDTO>> findByPostCode(String postCode);
	ResponseEntity<List<AddressDTO>> findByTown(String town);
	ResponseEntity<List<AddressDTO>> findByPostCodeAndTown(String postCode, String Town);
	ResponseEntity<List<AddressDTO>> findByPostCodeAndTownAndDistrict(String postCode, String town, String district);
}
