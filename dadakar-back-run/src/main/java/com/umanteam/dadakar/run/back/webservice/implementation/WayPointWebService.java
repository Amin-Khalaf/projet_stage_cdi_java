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

import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IWayPointService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IWayPointWebService;

@RestController
@RequestMapping("${appli.path}//waypoints")
@CrossOrigin(origins="*")
public class WayPointWebService implements IWayPointWebService {

	@Autowired
	private IWayPointService waypointService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public WayPointDTO add(@RequestBody WayPointDTO waypoint) {
		return waypointService.add(waypoint);
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	@Override
	public WayPointDTO update(@RequestBody WayPointDTO waypoint) {
		return waypointService.update(waypoint);
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		waypointService.delete(id);
	}

	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WayPointDTO>> findAll() {
		List<WayPointDTO> waypoints = waypointService.findAll();
		if (waypoints == null) {
			return new ResponseEntity<List<WayPointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WayPointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<WayPointDTO> findById(@PathVariable("id") String id) {
		WayPointDTO waypoint = waypointService.findById(id);
		if (waypoint == null) {
			return new ResponseEntity<WayPointDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<WayPointDTO>(waypoint, HttpStatus.OK);
	}

	@RequestMapping(value="/district/{district}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WayPointDTO>> findByDistrict(@PathVariable("district") String district) {
		System.out.println(district);
		List<WayPointDTO> waypoints = waypointService.findByDistrict(district);
		if (waypoints == null) {
			return new ResponseEntity<List<WayPointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WayPointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/town/{town}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WayPointDTO>> findByTown(@PathVariable("town") String town) {
		System.out.println(town);
		List<WayPointDTO> waypoints = waypointService.findByTown(town);
		if (waypoints == null) {
			return new ResponseEntity<List<WayPointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WayPointDTO>>(waypoints, HttpStatus.OK);
	}

	@RequestMapping(value="/postcode/{postcode}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<WayPointDTO>> findByPostcode(@PathVariable("postcode") String postcode) {
		System.out.println(postcode);
		List<WayPointDTO> waypoints = waypointService.findByPostcode(postcode);
		if (waypoints == null) {
			return new ResponseEntity<List<WayPointDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<WayPointDTO>>(waypoints, HttpStatus.OK);
	}

}
