package com.umanteam.dadakar.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.DTO.VehicleDTO;

public interface IVehicleWebService {

	public VehicleDTO addVehicle(VehicleDTO vehicle);
	public VehicleDTO updateVehicle(VehicleDTO vehicle);
	public void deleteVehicle(VehicleDTO vehicle);
	public ResponseEntity<List<VehicleDTO>> findAllVehicle();
	public VehicleDTO findVehicle(String vehicleId);

}
