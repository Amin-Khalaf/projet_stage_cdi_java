package com.umanteam.dadakar.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.dto.VehicleDTO;

public interface IVehicleService {

	public VehicleDTO add(VehicleDTO vehicle);
	public VehicleDTO update(VehicleDTO vehicle);
	public void delete(String id);
	public List<VehicleDTO> findAll();
	public VehicleDTO findById(String id);
}
