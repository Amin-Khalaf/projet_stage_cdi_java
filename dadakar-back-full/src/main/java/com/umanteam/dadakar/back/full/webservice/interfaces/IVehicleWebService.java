package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.VehicleDTO;

public interface IVehicleWebService {

	public VehicleDTO addVehicle(VehicleDTO vehicle);
	public VehicleDTO updateVehicle(VehicleDTO vehicle);
	public void deleteVehicle(String id);
	public ResponseEntity<List<VehicleDTO>> findAllVehicle();
	public VehicleDTO findVehicle(String id);

}
