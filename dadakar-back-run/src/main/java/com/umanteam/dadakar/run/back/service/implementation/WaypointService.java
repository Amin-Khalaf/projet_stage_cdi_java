package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.WaypointDTO;
import com.umanteam.dadakar.run.back.entities.Waypoint;
import com.umanteam.dadakar.run.back.repository.WaypointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IWaypointService;

@Service
public class WaypointService implements IWaypointService {

	@Autowired
	WaypointRepository waypointRepository;
	
	@Override
	public WaypointDTO add(WaypointDTO waypoint) {
		Waypoint entity = new Waypoint();
		BeanUtils.copyProperties(waypoint, entity);
		entity = waypointRepository.insert(entity);
		BeanUtils.copyProperties(entity, waypoint);
		return waypoint;
	}

	@Override
	public WaypointDTO update(WaypointDTO waypoint) {
		Waypoint entity = new Waypoint();
		BeanUtils.copyProperties(waypoint, entity);
		entity = waypointRepository.save(entity);
		BeanUtils.copyProperties(entity, waypoint);
		return waypoint;
	}

	@Override
	public void delete(String id) {
		waypointRepository.delete(id);
	}

	@Override
	public List<WaypointDTO> findAll() {
		List<WaypointDTO> waypoints = new ArrayList<>();
		for (Waypoint entity : waypointRepository.findAll()) {
			WaypointDTO waypoint = new WaypointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public WaypointDTO findById(String id) {
		Waypoint entity = waypointRepository.findOne(id);
		WaypointDTO waypoint = new WaypointDTO();
		BeanUtils.copyProperties(entity, waypoint);
		return waypoint;
	}

	@Override
	public List<WaypointDTO> findByDistrict(String district) {
		List<WaypointDTO> waypoints = new ArrayList<>();
		for (Waypoint entity : waypointRepository.findByDistrict(district)) {
			WaypointDTO waypoint = new WaypointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public List<WaypointDTO> findByTown(String town) {
		List<WaypointDTO> waypoints = new ArrayList<>();
		for (Waypoint entity : waypointRepository.findByTown(town)) {
			WaypointDTO waypoint = new WaypointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public List<WaypointDTO> findByPostcode(String postcode) {
		List<WaypointDTO> waypoints = new ArrayList<>();
		for (Waypoint entity : waypointRepository.findByPostcode(postcode)) {
			WaypointDTO waypoint = new WaypointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

}
