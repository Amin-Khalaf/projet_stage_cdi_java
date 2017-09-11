package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.VehicleDTO;
import com.umanteam.dadakar.run.back.entities.Vehicle;
import com.umanteam.dadakar.run.back.repository.VehiculeRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IVehicleService;

@Service("vehicleService")
public class VehicleService implements IVehicleService {

	@Autowired
	private VehiculeRepository vehicleRepository;
	
	/* copy from VehicleDTO to Vehicle */
	private Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO) {
		Vehicle entity = new Vehicle();
		BeanUtils.copyProperties(vehicleDTO, entity);
		return entity;
	}
	
	/* copy form Vehicle to VehicleDTO */
	private VehicleDTO vehicleToVehicleDTO(Vehicle entity) {
		VehicleDTO vehicle = new VehicleDTO();
		BeanUtils.copyProperties(entity, vehicle);
		return vehicle;
	}
	
	@Override
	public VehicleDTO addOrUpdate(VehicleDTO vehicle) {
		return vehicleToVehicleDTO(vehicleRepository.save(vehicleDTOToVehicle(vehicle)));
	}

	@Override
	public void delete(String id) {
		vehicleRepository.delete(id);
	}

	@Override
	public List<VehicleDTO> findAll() {
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		List<Vehicle> vehicles = vehicleRepository.findAll();
		if(vehicles != null) for (Vehicle entity : vehicles) vehicleDTOs.add(vehicleToVehicleDTO(entity));
		return vehicleDTOs;
	}

	@Override
	public VehicleDTO findById(String vehicleId) {
		Vehicle vehicle = vehicleRepository.findOne(vehicleId);
		if(vehicle != null) return vehicleToVehicleDTO(vehicle);
		return new VehicleDTO();
	}

}
