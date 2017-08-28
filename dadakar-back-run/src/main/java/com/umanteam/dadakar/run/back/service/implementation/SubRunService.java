package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.repository.SubRunRepository;
import com.umanteam.dadakar.run.back.service.interfaces.ISubRunService;

@Service("subRunService")
public class SubRunService implements ISubRunService {
	
	@Autowired
	private SubRunRepository subRunRepository;
	
	/* copy from SubRunDTO to SubRun */
	private SubRun subRunDTOToSubRun(SubRunDTO subRunDTO) {
		SubRun subRun = new SubRun();
		WayPoint wp = new WayPoint();
		List<Passenger> passengers = new ArrayList<>();
		List<WayPoint> wayPoints = new ArrayList<>();
		List<Toll> tolls = new ArrayList<>();
		BeanUtils.copyProperties(subRunDTO, subRun);
		BeanUtils.copyProperties(subRunDTO.getStartPlace(), wp);
		subRun.setStartPlace(wp);
		wp = new WayPoint();
		BeanUtils.copyProperties(subRunDTO.getEndPlace(), wp);
		subRun.setEndPlace(wp);
		if(subRunDTO.getPassengers() != null) {
			for(PassengerDTO passengerDTO: subRunDTO.getPassengers()) {
				Passenger passenger = new Passenger();
				BeanUtils.copyProperties(passengerDTO, passenger);
				passengers.add(passenger);
			}
		}
		subRun.setPassengers(passengers);
		if(subRunDTO.getStartingPoints() != null) {
			for(WayPointDTO wayPointDTO: subRunDTO.getStartingPoints()) {
				wp = new WayPoint();
				BeanUtils.copyProperties(wayPointDTO, wp);
				wayPoints.add(wp);
			}
		}
		subRun.setStartingPoints(wayPoints);
		if(subRunDTO.getTolls() != null) {
			for(TollDTO tollDTO: subRunDTO.getTolls()) {
				Toll toll = new Toll();
				BeanUtils.copyProperties(tollDTO, toll);
				tolls.add(toll);
			}
		}
		subRun.setTolls(tolls);
		return subRun;
	}
	
	/* copy from SubRun to SubRunDTO */
	private SubRunDTO subRunToSubRunDTO(SubRun subRun) {
		SubRunDTO subRunDTO = new SubRunDTO();
		WayPointDTO wpDTO = new WayPointDTO();
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		List<WayPointDTO> wayPointDTOs = new ArrayList<>();
		List<TollDTO> tollDTOs = new ArrayList<>();
		BeanUtils.copyProperties(subRun, subRunDTO);
		BeanUtils.copyProperties(subRun.getStartPlace(), wpDTO);
		subRunDTO.setStartPlace(wpDTO);
		wpDTO = new WayPointDTO();
		BeanUtils.copyProperties(subRun.getEndPlace(), wpDTO);
		subRunDTO.setEndPlace(wpDTO);
		if(subRun.getPassengers() != null) {
			for(Passenger passenger: subRun.getPassengers()) {
				PassengerDTO passengerDTO = new PassengerDTO();
				BeanUtils.copyProperties(passenger, passengerDTO);
				passengerDTOs.add(passengerDTO);
			}
		}
		subRunDTO.setPassengers(passengerDTOs);
		if(subRun.getStartingPoints() != null) {
			for(WayPoint wayPoint: subRun.getStartingPoints()) {
				wpDTO = new WayPointDTO();
				BeanUtils.copyProperties(wayPoint, wpDTO);
				wayPointDTOs.add(wpDTO);
			}
		}
		subRunDTO.setStartingPoints(wayPointDTOs);
		if(subRun.getTolls() != null) {
			for(Toll toll: subRun.getTolls()) {
				TollDTO tollDTO = new TollDTO();
				BeanUtils.copyProperties(toll, tollDTO);
				tollDTOs.add(tollDTO);
			}
		}
		subRunDTO.setTolls(tollDTOs);
		return subRunDTO;
	}

	@Override
	public SubRunDTO addOrUpdate(SubRunDTO subRunDTO) {
		return subRunToSubRunDTO(subRunRepository.save(subRunDTOToSubRun(subRunDTO)));
	}

	@Override
	public void delete(String id) {
		subRunRepository.delete(id);
	}

	@Override
	public List<SubRunDTO> findAll() {
		List<SubRunDTO> subRunDTOs = new ArrayList<>();
		for(SubRun subRun: subRunRepository.findAll()) subRunDTOs.add(subRunToSubRunDTO(subRun));
		return subRunDTOs;
	}

	@Override
	public SubRunDTO findById(String id) {
		return subRunToSubRunDTO(subRunRepository.findOne(id));
	}

}
