package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.VehicleDTO;

public interface IVehicleService {
	public VehicleDTO addOrUpdate(VehicleDTO vehicle);
	public void delete(String id);
	public List<VehicleDTO> findAll();
	public VehicleDTO findById(String id);
}
