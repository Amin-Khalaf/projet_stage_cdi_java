package com.umanteam.dadakar.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.service.implementation.VehicleService;
import com.umanteam.dadakar.back.webservice.interfaces.IVehicleWebService;

@RestController
@RequestMapping("/ws/vehicles")
@CrossOrigin(origins="*")
public class VehicleWebService implements IVehicleWebService {

	@Autowired
	private VehicleService vehicleService;
	
	@Override
	@RequestMapping(value="/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VehicleDTO addVehicle(@RequestBody VehicleDTO vehicle) {
		return vehicleService.add(vehicle);
	}

	@Override
	@RequestMapping(value="/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public VehicleDTO updateVehicle(@RequestBody VehicleDTO vehicle) {
		return vehicleService.update(vehicle);
	}

	@Override
	@RequestMapping(value="/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteVehicle(@RequestBody VehicleDTO vehicle) {
		vehicleService.delete(vehicle);
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VehicleDTO>> findAllVehicle() {
		List<VehicleDTO> vehicles = vehicleService.findAll();
		if (vehicles.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<VehicleDTO>>(vehicles, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public VehicleDTO findVehicle(@PathVariable("id") String vehicleId) {
		return vehicleService.findOne(vehicleId);
	}

}
