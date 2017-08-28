package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.WayPointDTO;

public interface IWayPointService {

	public WayPointDTO addOrUpdate(WayPointDTO waypoint);
	public void delete(String id);
	public List<WayPointDTO> findAll();
	public WayPointDTO findById(String id);
	public List<WayPointDTO> findByDistrict(String district);
	public List<WayPointDTO> findByTown(String town);
	public List<WayPointDTO> findByPostcode(String postcode);
}
