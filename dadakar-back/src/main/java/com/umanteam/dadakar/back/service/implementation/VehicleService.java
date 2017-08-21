package com.umanteam.dadakar.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.DTO.VehicleDTO;
import com.umanteam.dadakar.back.entity.Vehicle;
import com.umanteam.dadakar.back.repository.VehiculeRepository;
import com.umanteam.dadakar.back.service.interfaces.IVehicleService;

@Service("vehicleService")
public class VehicleService implements IVehicleService {

	@Autowired
	private VehiculeRepository vehicleRepository;
	
	@Override
	public VehicleDTO add(VehicleDTO vehicle) {
		Vehicle entity = new Vehicle();
		BeanUtils.copyProperties(vehicle, entity);
		entity = vehicleRepository.insert(entity);
		BeanUtils.copyProperties(entity, vehicle);
		return vehicle;
	}

	@Override
	public VehicleDTO update(VehicleDTO vehicle) {
		Vehicle entity = new Vehicle();
		BeanUtils.copyProperties(vehicle, entity);
		entity = vehicleRepository.save(entity);
		BeanUtils.copyProperties(entity, vehicle);
		return vehicle;
	}

	@Override
	public void delete(VehicleDTO vehicle) {
		Vehicle entity = new Vehicle();
		BeanUtils.copyProperties(vehicle, entity);
		vehicleRepository.delete(entity);
	}

	@Override
	public List<VehicleDTO> findAll() {
		List<VehicleDTO> vehicles = new ArrayList<>();
		for (Vehicle entity : vehicleRepository.findAll()){
			VehicleDTO vehicle = new VehicleDTO();
			BeanUtils.copyProperties(entity, vehicle);
			vehicles.add(vehicle);
		}
		return vehicles;
	}

	@Override
	public VehicleDTO findOne(String vehicleId) {
		Vehicle entity = vehicleRepository.findOne(vehicleId);
		VehicleDTO vehicle = new VehicleDTO();
		BeanUtils.copyProperties(entity, vehicle);
		return vehicle;
	}

}
