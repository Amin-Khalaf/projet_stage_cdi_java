package com.umanteam.dadakar.run.back.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;

@Service
public class RunService implements IRunService {

	@Autowired
	RunRepository runRepository;
	
	@Override
	public RunDTO addRun(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		entity = runRepository.insert(entity);
		BeanUtils.copyProperties(entity, run);
		return run;
	}

	@Override
	public RunDTO updateRun(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		entity = runRepository.save(entity);
		BeanUtils.copyProperties(entity, run);
		return run;
	}

	@Override
	public void deleteRun(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		runRepository.delete(entity);
	}

	@Override
	public List<RunDTO> findAllRuns() {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findAll()){
			RunDTO run = new RunDTO();
			BeanUtils.copyProperties(entity, run);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public RunDTO findRunsById(String id) {
		RunDTO run = new RunDTO();
		BeanUtils.copyProperties(runRepository.findOne(id), run);
		return run;
	}

	@Override
	public List<RunDTO> findRunsByDriver(UserDTO driver) {
		User driverEntity = new User();
		BeanUtils.copyProperties(driver, driverEntity);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriver(driverEntity)){
			RunDTO run = new RunDTO();
			BeanUtils.copyProperties(entity, run);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByPassenger(UserDTO passenger) {
		User passengerEntity = new User();
		BeanUtils.copyProperties(passenger, passengerEntity);
		List<RunDTO> runs = new ArrayList<>();
		// TODO : change to method that sends back only runs when passenger is not refused or canceled
		for (Run entity : runRepository.findByPassengersUser(passengerEntity)) {
			RunDTO run = new RunDTO();
			BeanUtils.copyProperties(entity, run);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo,
			String townTo) {
		// TODO Auto-generated method stub
		return null;
	}

}
