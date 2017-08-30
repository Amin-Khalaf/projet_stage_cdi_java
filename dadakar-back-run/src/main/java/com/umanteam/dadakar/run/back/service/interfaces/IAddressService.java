package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.AddressDTO;

public interface IAddressService {
	AddressDTO addOrUpdate(AddressDTO addressDTO);
	void delete(String id);
	List<AddressDTO> findAll();
	AddressDTO findById(String id);
	List<AddressDTO> findByTown(String town);
	AddressDTO findByTownAndDistrict(String town, String district);
}
