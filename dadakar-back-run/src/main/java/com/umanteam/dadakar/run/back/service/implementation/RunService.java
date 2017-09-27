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

import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Address;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.User;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.ResState;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;

@Service
public class RunService implements IRunService {

	@Autowired
	RunRepository runRepository;

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
			entity.setAddress(address);
			// add to map
			waypoints.put(waypointKey, entity);
		}
		return entity;
	}

	private Run copyDtoToEntity(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		// copy driver to dto and assign to run
		User user = new User();
		BeanUtils.copyProperties(run.getDriver(), user);
		entity.setDriver(user);
		if (run.getSubRuns() != null) {
			// copy subRun entity to dto and assign to run
			List<SubRun> subRunsentity = new ArrayList<>();
			// map to manage waypoints unicity
			Map<String, WayPoint> unicWaypoints = new HashMap<>();
			for (SubRunDTO subRun : run.getSubRuns()) {
				SubRun subRunEntity = new SubRun();
				BeanUtils.copyProperties(subRun, subRunEntity);
				// copy startPlace
				WayPoint startplaceEntity = saveUnicWaypoints(subRun.getStartPlace(), unicWaypoints);
				// assign it to subRun
				subRunEntity.setStartPlace(startplaceEntity);
				// copy endPlace
				WayPoint endplaceEntity = saveUnicWaypoints(subRun.getEndPlace(), unicWaypoints);
				// assign it to subRun
				subRunEntity.setEndPlace(endplaceEntity);
				// copy passengers entity to dto and assign to subRun
				if (subRun.getPassengers() != null) {
					List<Passenger> passengersEntity = new ArrayList<>();
					for (PassengerDTO passenger : subRun.getPassengers()) {
						Passenger passengerEntity = new Passenger();
						BeanUtils.copyProperties(passenger, passengerEntity);
						//copy user to dto and assign to passenger
						user = new User();
						BeanUtils.copyProperties(passenger.getUser(), user);
						passengerEntity.setUser(user);
						// add to list
						passengersEntity.add(passengerEntity);
					}
					subRunEntity.setPassengers(passengersEntity);
				}
				// copy startingPoints
				List<WayPoint> waypointsEntity = new ArrayList<>();
				for (WayPointDTO waypoint : subRun.getStartingPoints()) {
					WayPoint waypointEntity = saveUnicWaypoints(waypoint, unicWaypoints);
					waypointsEntity.add(waypointEntity);
				}
				subRunEntity.setStartingPoints(waypointsEntity);
				// copy tolls
				if (subRun.getTolls() != null) {
					List<Toll> tollsEntity = new ArrayList<>();
					for (TollDTO toll : subRun.getTolls()) {
						Toll tollEntity = new Toll();
						BeanUtils.copyProperties(toll, tollEntity);
						tollsEntity.add(tollEntity);
					}
					subRunEntity.setTolls(tollsEntity);
				}
				// add subRun to list
				subRunsentity.add(subRunEntity);
			}
			entity.setSubRuns(subRunsentity);
		}
		return entity;
	}

	private RunDTO copyEntityToDto(Run entity) {
		// copy run entity to DTO
		RunDTO run = new RunDTO();
		BeanUtils.copyProperties(entity, run);
		// copy user to dto and assign to run
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(entity.getDriver(), userDTO);
		run.setDriver(userDTO);
		// copy vehicle entity to dto and assign to run
		if (entity.getSubRuns() != null) {
			// copy subRun entity to dto and assign to run
			List<SubRunDTO> subRuns = new ArrayList<>();
			for (SubRun subRunEntity : entity.getSubRuns()) {
				SubRunDTO subRun = new SubRunDTO();
				BeanUtils.copyProperties(subRunEntity, subRun);
				// copy startPlace
				if (subRunEntity.getStartPlace() != null) {
					WayPointDTO startPlace = new WayPointDTO();
					BeanUtils.copyProperties(subRunEntity.getStartPlace(), startPlace);
					if (subRunEntity.getStartPlace().getAddress() != null) {
						Address startpointAddress = new Address();
						BeanUtils.copyProperties(subRunEntity.getStartPlace().getAddress(), startpointAddress);
						startPlace.setAddress(startpointAddress);
					}
					subRun.setStartPlace(startPlace);
				}
				// copy endPlace
				if (subRunEntity.getEndPlace() != null) {
					WayPointDTO endPlace = new WayPointDTO();
					BeanUtils.copyProperties(subRunEntity.getEndPlace(), endPlace);
					if (subRunEntity.getEndPlace().getAddress() != null) {
						Address endplaceAddress = new Address();
						BeanUtils.copyProperties(subRunEntity.getEndPlace().getAddress(), endplaceAddress);
						endPlace.setAddress(endplaceAddress);
					}
					subRun.setEndPlace(endPlace);
				}
				// copy passengers entity to dto and assign to subRun
				if (subRunEntity.getPassengers() != null) {
					List<PassengerDTO> passengers = new ArrayList<>();
					for (Passenger passengerEntity : subRunEntity.getPassengers()) {
						PassengerDTO passenger = new PassengerDTO();
						BeanUtils.copyProperties(passengerEntity, passenger);
						// copy user to dto and assign to passenger
						userDTO = new UserDTO();
						BeanUtils.copyProperties(passengerEntity.getUser(), userDTO);
						passenger.setUser(userDTO);
						passengers.add(passenger);
					}
					subRun.setPassengers(passengers);
				}
				// copy startingPoints
				if (subRunEntity.getStartingPoints() != null) {
					List<WayPointDTO> startingPoints = new ArrayList<>();
					for (WayPoint waypointEntity : subRunEntity.getStartingPoints()) {
						WayPointDTO startingpoint = new WayPointDTO();
						BeanUtils.copyProperties(waypointEntity, startingpoint);
						if (waypointEntity.getAddress() != null) {
							Address startingpointAddress = new Address();
							BeanUtils.copyProperties(waypointEntity.getAddress(), startingpointAddress);
							startingpoint.setAddress(startingpointAddress);
						}
						startingPoints.add(startingpoint);
					}
					subRun.setStartingPoints(startingPoints);
				}
				// copy tolls
				if (subRunEntity.getTolls() != null) {
					List<TollDTO> tolls = new ArrayList<>();
					for (Toll tollEntity : subRunEntity.getTolls()) {
						TollDTO toll = new TollDTO();
						BeanUtils.copyProperties(tollEntity, toll);
						tolls.add(toll);
					}
					subRun.setTolls(tolls);
				}
				// add subRun to list
				subRuns.add(subRun);
			}
			run.setSubRuns(subRuns);
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
		for (Run entity : runRepository.findByDriverUserId(driverId)) {
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
		List<Run> entities = runRepository.findByDriverUserIdAndCancelled(driverId, false);
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
		for (Run entity : runRepository.findBySubRunsPassengersUserUserId(passengerId)) {
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
				.findBySubRunsPassengersUserUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(passengerId, false,
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
		for (Run entity : runRepository.findByDriverUserIdOrSubRunsPassengersUserUserId(userId, userId)) {
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
				.findByDriverUserIdOrSubRunsPassengersUserUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(userId,
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
				.findByDriverUserIdOrSubRunsPassengersUserUserIdAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
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
				.findByDriverUserIdOrSubRunsPassengersUserUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
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
		List<Run> entity = runRepository.findByDriverUserIdOrSubRunsPassengersUserUserIdAndSubRunsEstimatedEndDateLessThan(
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
				.findByDriverUserIdOrSubRunsPassengersUserUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateLessThan(
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
		// search for runs that have a matching subRun
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
