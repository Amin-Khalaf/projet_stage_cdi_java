package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.VehicleDTO;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.repository.VehiculeRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IVehicleService;

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
		List<VehicleDTO> vehicles = new ArrayList<>();
		for (Vehicle entity : vehicleRepository.findAll()) vehicles.add(vehicleToVehicleDTO(entity));
		return vehicles;
	}

	@Override
	public VehicleDTO findById(String vehicleId) {
		VehicleDTO vehicle = new VehicleDTO();
		Vehicle entity = vehicleRepository.findOne(vehicleId);
		if (entity != null)
			vehicle = vehicleToVehicleDTO(entity);
		return vehicle;
	}

}
