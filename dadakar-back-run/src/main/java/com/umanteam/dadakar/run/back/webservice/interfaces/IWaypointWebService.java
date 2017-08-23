package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.WaypointDTO;

public interface IWaypointWebService {
	
	public WaypointDTO add(WaypointDTO waypoint);
	public WaypointDTO update(WaypointDTO waypoint);
	public void delete(String id);
	public ResponseEntity<List<WaypointDTO>> findAll();
	public ResponseEntity<WaypointDTO> findById(String id);
	public ResponseEntity<List<WaypointDTO>> findByDistrict(String district);
	public ResponseEntity<List<WaypointDTO>> findByTown(String town);
	public ResponseEntity<List<WaypointDTO>> findByPostcode(String postcode);
}
