package com.umanteam.dadakar.run.back.service.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Address;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.ResState;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.repository.SubRunRepository;
import com.umanteam.dadakar.run.back.repository.WayPointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;

@Service
public class RunService implements IRunService {

	@Autowired
	RunRepository runRepository;

	@Autowired
	WayPointRepository waypointRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	SubRunRepository subrunRepository;

	private WayPoint saveUnicWaypoints(WayPointDTO waypoint, Map<String, WayPoint> waypoints) {
		String waypointKey = "";
		WayPoint entity = new WayPoint();
		waypointKey = waypoint.getMeetingPoint() + "|" + waypoint.getAddress().toString();
		if (waypoints.containsKey(waypointKey)) {
			// the waypoint has already been recorded
			entity = waypoints.get(waypointKey);
		} else {
			// waypoint not recorded
			BeanUtils.copyProperties(waypoint, entity);
			// copy address
			Address address = new Address();
			BeanUtils.copyProperties(waypoint.getAddress(), address);
			// save
			entity = waypointRepository.save(entity);
			// add to map
			waypoints.put(waypointKey, entity);
		}
		return entity;
	}

	private Run copyDtoToEntity(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		if (run.getVehicle() != null) {
			// copy vehicle dto to entity
			Vehicle vehicleEntity = new Vehicle();
			BeanUtils.copyProperties(run.getVehicle(), vehicleEntity);
			entity.setVehicle(vehicleEntity);
		}
		if (run.getSubruns() != null) {
			// copy subrun entity to dto and assign to run
			List<SubRun> subrunsentity = new ArrayList<>();
			// map to manage waypoints unicity
			Map<String, WayPoint> unicWaypoints = new HashMap<>();
			for (SubRunDTO subrun : run.getSubruns()) {
				SubRun subrunEntity = new SubRun();
				BeanUtils.copyProperties(subrun, subrunEntity);
				// copy startPlace
				WayPoint startplaceEntity = saveUnicWaypoints(subrun.getStartPlace(), unicWaypoints);
				// assign it to subrun
				subrunEntity.setStartPlace(startplaceEntity);
				// copy endPlace
				WayPoint endplaceEntity = saveUnicWaypoints(subrun.getEndPlace(), unicWaypoints);
				// assign it to subrun
				subrunEntity.setEndPlace(endplaceEntity);
				// copy passengers entity to dto and assign to subrun
				if (subrun.getPassengers() != null) {
					List<Passenger> passengersEntity = new ArrayList<>();
					for (PassengerDTO passenger : subrun.getPassengers()) {
						Passenger passengerEntity = new Passenger();
						BeanUtils.copyProperties(passenger, passengerEntity);
						// save passenger
						passengerEntity = passengerRepository.save(passengerEntity);
						// add to list
						passengersEntity.add(passengerEntity);
					}
					subrunEntity.setPassengers(passengersEntity);
				}
				// copy startingPoints
				List<WayPoint> waypointsEntity = new ArrayList<>();
				for (WayPointDTO waypoint : subrun.getStartingPoints()) {
					WayPoint waypointEntity = saveUnicWaypoints(waypoint, unicWaypoints);
					waypointsEntity.add(waypointEntity);
				}
				subrunEntity.setStartingPoints(waypointsEntity);
				// copy tolls
				if (subrun.getTolls() != null) {
					List<Toll> tollsEntity = new ArrayList<>();
					for (TollDTO toll : subrun.getTolls()) {
						Toll tollEntity = new Toll();
						BeanUtils.copyProperties(toll, tollEntity);
						tollsEntity.add(tollEntity);
					}
					subrunEntity.setTolls(tollsEntity);
				}
				// save subrun
				subrunRepository.save(subrunEntity);
				// add subrun to list
				subrunsentity.add(subrunEntity);
			}
			entity.setSubRuns(subrunsentity);
		}
		return entity;
	}

	private RunDTO copyEntityToDto(Run entity) {
		// copy run entity to DTO
		RunDTO run = new RunDTO();
		BeanUtils.copyProperties(entity, run);
		// copy vehicle entity to dto and assign to run
		if (entity.getVehicle() != null) {
			VehicleDTO vehicle = new VehicleDTO();
			BeanUtils.copyProperties(entity.getVehicle(), vehicle);
			run.setVehicle(vehicle);
		}
		if (entity.getSubRuns() != null) {
			// copy subrun entity to dto and assign to run
			List<SubRunDTO> subruns = new ArrayList<>();
			for (SubRun subrunEntity : entity.getSubRuns()) {
				SubRunDTO subrun = new SubRunDTO();
				BeanUtils.copyProperties(subrunEntity, subrun);
				// copy startPlace
				if (subrunEntity.getStartPlace() != null) {
					WayPointDTO startPlace = new WayPointDTO();
					BeanUtils.copyProperties(subrunEntity.getStartPlace(), startPlace);
					if (subrunEntity.getStartPlace().getAddress() != null) {
						Address startpointAddress = new Address();
						BeanUtils.copyProperties(subrunEntity.getStartPlace().getAddress(), startpointAddress);
						startPlace.setAddress(startpointAddress);
					}
					subrun.setStartPlace(startPlace);
				}
				// copy endPlace
				if (subrunEntity.getEndPlace() != null) {
					WayPointDTO endPlace = new WayPointDTO();
					BeanUtils.copyProperties(subrunEntity.getEndPlace(), endPlace);
					if (subrunEntity.getEndPlace().getAddress() != null) {
						Address endplaceAddress = new Address();
						BeanUtils.copyProperties(subrunEntity.getEndPlace().getAddress(), endplaceAddress);
						endPlace.setAddress(endplaceAddress);
					}
					subrun.setEndPlace(endPlace);
				}
				// copy passengers entity to dto and assign to subrun
				if (subrunEntity.getPassengers() != null) {
					List<PassengerDTO> passengers = new ArrayList<>();
					for (Passenger passengerEntity : subrunEntity.getPassengers()) {
						PassengerDTO passenger = new PassengerDTO();
						BeanUtils.copyProperties(passengerEntity, passenger);
						passengers.add(passenger);
					}
					subrun.setPassengers(passengers);
				}
				// copy startingPoints
				if (subrunEntity.getStartingPoints() != null) {
					List<WayPointDTO> startingPoints = new ArrayList<>();
					for (WayPoint waypointEntity : subrunEntity.getStartingPoints()) {
						WayPointDTO startingpoint = new WayPointDTO();
						BeanUtils.copyProperties(waypointEntity, startingpoint);
						if (waypointEntity.getAddress() != null) {
							Address startingpointAddress = new Address();
							BeanUtils.copyProperties(waypointEntity.getAddress(), startingpointAddress);
							startingpoint.setAddress(startingpointAddress);
						}
						startingPoints.add(startingpoint);
					}
					subrun.setStartingPoints(startingPoints);
				}
				// copy tolls
				if (subrunEntity.getTolls() != null) {
					List<TollDTO> tolls = new ArrayList<>();
					for (Toll tollEntity : subrunEntity.getTolls()) {
						TollDTO toll = new TollDTO();
						BeanUtils.copyProperties(tollEntity, toll);
						tolls.add(toll);
					}
					subrun.setTolls(tolls);
				}
				// add subrun to list
				subruns.add(subrun);
			}
			run.setSubruns(subruns);
		}
		return run;
	}

	@Override
	public RunDTO addRun(RunDTO run) {
		Run entity = copyDtoToEntity(run);
		// save
		entity = runRepository.insert(entity);
		run = copyEntityToDto(entity);
		return run;
	}

	@Override
	public RunDTO updateRun(RunDTO run) {
		// copy run dto to entity
		Run entity = copyDtoToEntity(run);
		// save
		entity = runRepository.save(entity);
		run = copyEntityToDto(entity);
		return run;
	}

	@Override
	public void deleteRun(String id) {
		runRepository.delete(id);
	}

	@Override
	public List<RunDTO> findAllRuns() {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findAll()) {
			RunDTO run = copyEntityToDto(entity);
			// add run to list
			runs.add(run);
		}
		return runs;
	}

	@Override
	public RunDTO findRunsById(String id) {
		RunDTO run = new RunDTO();
		Run entity = runRepository.findOne(id);
		run = copyEntityToDto(entity);
		return run;
	}

	@Override
	public List<RunDTO> findRunsByDriverId(String driverId) {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriverId(driverId)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	// TODO: TEST
	@Override
	public List<RunDTO> findRunsNotCancelledByDriverId(String driverId) {
		List<RunDTO> runs = new ArrayList<>();
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.RUN_CANCELED);
		List<Run> entities = runRepository.findByDriverIdAndCancelled(driverId, false);
		// Copy run entity to dto
		if (entities != null) {
			for (Run entity : entities) {
				RunDTO run = copyEntityToDto(entity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByPassengerId(String passengerId) {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findBySubRunsPassengersUserId(passengerId)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByPassengerId(String passengerId) {
		List<RunDTO> runs = new ArrayList<>();
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.CANCELLED);
		resStates.add(ResState.RUN_CANCELED);
		List<Run> entity = runRepository
				.findBySubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(passengerId, false,
						resStates);
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriverIdOrSubRunsPassengersUserId(userId, userId)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.CANCELLED);
		resStates.add(ResState.RUN_CANCELED);
		List<Run> entity = runRepository
				.findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(userId,
						userId, false, resStates);
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findCurrentRunsByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository
				.findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
						userId, userId, LocalDate.now(), LocalTime.now());
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findCurrentRunsNotCancelledByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.CANCELLED);
		resStates.add(ResState.RUN_CANCELED);
		List<Run> entity = runRepository
				.findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
						userId, userId, false, resStates, LocalDate.now(), LocalTime.now());
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findPassedRunsByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateLessThan(
				userId, userId, LocalDate.now());
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findPassedRunsNotCancelledByUserId(String userId) {
		List<RunDTO> runs = new ArrayList<>();
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.CANCELLED);
		resStates.add(ResState.RUN_CANCELED);
		List<Run> entity = runRepository
				.findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateLessThan(
						userId, userId, false, resStates, LocalDate.now());
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo,
			String townTo) {
		List<RunDTO> runs = new ArrayList<>();
		// search for runs that have a matching subrun
		List<Run> entity = runRepository
				.findBySubRunsStartingPointsAddressDistrictAndSubRunsStartingPointsAddressTownAndSubRunsStartDateAndSubRunsEndPlaceAddressDistrictAndSubRunsEndPlaceAddressTownAndSubRunsAvailableSeatsGreaterThanAndCancelled(
						districtFrom, townFrom, dateStart, districtTo, townTo, 0, false);
		if (entity != null) {
			for (Run runEntity : entity) {
				RunDTO run = copyEntityToDto(runEntity);
				runs.add(run);
			}
		}
		return runs;
	}

}
