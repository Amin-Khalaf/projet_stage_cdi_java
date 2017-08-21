package com.umanteam.dadakar.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.DTO.VehicleDTO;

public interface IVehicleService {

	public VehicleDTO add(VehicleDTO vehicle);
	public VehicleDTO update(VehicleDTO vehicle);
	public void delete(VehicleDTO vehicle);
	public List<VehicleDTO> findAll();
	public VehicleDTO findOne(String vehicleId);
}
