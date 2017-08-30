package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.WayPointDTO;

public interface IWayPointWebService {
	public WayPointDTO add(WayPointDTO waypoint);
	public WayPointDTO update(WayPointDTO waypoint);
	public void delete(String id);
	public ResponseEntity<List<WayPointDTO>> findAll();
	public ResponseEntity<WayPointDTO> findById(String id);
}
