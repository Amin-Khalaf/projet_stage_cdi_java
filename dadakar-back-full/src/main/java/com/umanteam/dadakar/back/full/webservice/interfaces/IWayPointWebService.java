package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.WayPointDTO;

public interface IWayPointWebService {
	
	public WayPointDTO add(WayPointDTO waypoint);
	public WayPointDTO update(WayPointDTO waypoint);
	public void delete(String id);
	public ResponseEntity<List<WayPointDTO>> findAll();
	public ResponseEntity<WayPointDTO> findById(String id);
	public ResponseEntity<List<WayPointDTO>> findByDistrict(String district);
	public ResponseEntity<List<WayPointDTO>> findByTown(String town);
	public ResponseEntity<List<WayPointDTO>> findByPostcode(String postcode);
}
