package com.umanteam.dadakar.run.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.run.back.dto.WaypointDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IWaypointService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IWaypointWebService;

@RestController
@RequestMapping("/waypoints")
@CrossOrigin(origins="*")
public class WaypointWebService implements IWaypointWebService {

	@Autowired
	private IWaypointService waypointService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public WaypointDTO add(@RequestBody WaypointDTO waypoint) {
		return waypointService.add(waypoint);
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@Override
	public WaypointDTO update(@RequestBody WaypointDTO waypoint) {
		return waypointService.update(waypoint);
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		waypointService.delete(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WaypointDTO>> findAll() {
		List<WaypointDTO> waypoints = waypointService.findAll();
		if (waypoints == null) {
			return new ResponseEntity<List<WaypointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WaypointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<WaypointDTO> findById(@PathVariable("id") String id) {
		WaypointDTO waypoint = waypointService.findById(id);
		if (waypoint == null) {
			return new ResponseEntity<WaypointDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WaypointDTO>(waypoint, HttpStatus.OK);
	}

	@RequestMapping(value="/district/{district}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WaypointDTO>> findByDistrict(@PathVariable("district") String district) {
		System.out.println(district);
		List<WaypointDTO> waypoints = waypointService.findByDistrict(district);
		if (waypoints == null) {
			return new ResponseEntity<List<WaypointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WaypointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/town/{town}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WaypointDTO>> findByTown(@PathVariable("town") String town) {
		System.out.println(town);
		List<WaypointDTO> waypoints = waypointService.findByTown(town);
		if (waypoints == null) {
			return new ResponseEntity<List<WaypointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WaypointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/postcode/{postcode}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WaypointDTO>> findByPostcode(@PathVariable("postcode") String postcode) {
		System.out.println(postcode);
		List<WaypointDTO> waypoints = waypointService.findByPostcode(postcode);
		if (waypoints == null) {
			return new ResponseEntity<List<WaypointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WaypointDTO>>(waypoints, HttpStatus.OK);
	}

}
