package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.repository.WayPointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IWayPointService;

@Service
public class WayPointService implements IWayPointService {

	@Autowired
	WayPointRepository waypointRepository;
	
	@Override
	public WayPointDTO add(WayPointDTO waypoint) {
		WayPoint entity = new WayPoint();
		BeanUtils.copyProperties(waypoint, entity);
		entity = waypointRepository.insert(entity);
		BeanUtils.copyProperties(entity, waypoint);
		return waypoint;
	}

	@Override
	public WayPointDTO update(WayPointDTO waypoint) {
		WayPoint entity = new WayPoint();
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
	public List<WayPointDTO> findAll() {
		List<WayPointDTO> waypoints = new ArrayList<>();
		for (WayPoint entity : waypointRepository.findAll()) {
			WayPointDTO waypoint = new WayPointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public WayPointDTO findById(String id) {
		WayPoint entity = waypointRepository.findOne(id);
		WayPointDTO waypoint = new WayPointDTO();
		BeanUtils.copyProperties(entity, waypoint);
		return waypoint;
	}

	@Override
	public List<WayPointDTO> findByDistrict(String district) {
		List<WayPointDTO> waypoints = new ArrayList<>();
		for (WayPoint entity : waypointRepository.findByDistrict(district)) {
			WayPointDTO waypoint = new WayPointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public List<WayPointDTO> findByTown(String town) {
		List<WayPointDTO> waypoints = new ArrayList<>();
		for (WayPoint entity : waypointRepository.findByTown(town)) {
			WayPointDTO waypoint = new WayPointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

	@Override
	public List<WayPointDTO> findByPostcode(String postcode) {
		List<WayPointDTO> waypoints = new ArrayList<>();
		for (WayPoint entity : waypointRepository.findByPostcode(postcode)) {
			WayPointDTO waypoint = new WayPointDTO();
			BeanUtils.copyProperties(entity, waypoint);
			waypoints.add(waypoint);
		}
		return waypoints;
	}

}
