package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.AddressDTO;

public interface IAddressService {
	AddressDTO addOrUpdate(AddressDTO addressDTO);
	void delete(String id);
	List<AddressDTO> findAll();
	AddressDTO findById(String id);
	List<AddressDTO> findByPostCode(String postCode);
	List<AddressDTO> findByTown(String town);
	List<AddressDTO> findByPostCodeAndTown(String postCode, String Town);
	List<AddressDTO> findByPostCodeAndTownAndDistrict(String postCode, String town, String district);
}
