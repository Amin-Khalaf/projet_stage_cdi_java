package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.WaypointDTO;

public interface IWaypointService {

	public WaypointDTO add(WaypointDTO waypoint);
	public WaypointDTO update(WaypointDTO waypoint);
	public void delete(String id);
	public List<WaypointDTO> findAll();
	public WaypointDTO findById(String id);
	public List<WaypointDTO> findByDistrict(String district);
	public List<WaypointDTO> findByTown(String town);
	public List<WaypointDTO> findByPostcode(String postcode);
}
