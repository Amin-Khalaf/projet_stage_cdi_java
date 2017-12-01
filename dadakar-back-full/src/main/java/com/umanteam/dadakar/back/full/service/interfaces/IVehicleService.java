package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.VehicleDTO;

public interface IVehicleService {
	public VehicleDTO addOrUpdate(VehicleDTO vehicle);
	public void delete(String id);
	public List<VehicleDTO> findAll();
	public VehicleDTO findById(String id);
}
