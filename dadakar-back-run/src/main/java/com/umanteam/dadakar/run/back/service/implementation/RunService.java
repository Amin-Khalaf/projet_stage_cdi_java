package com.umanteam.dadakar.run.back.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.ResState;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;

@Service
public class RunService implements IRunService {

	@Autowired
	RunRepository runRepository;

	private Run copyDtoToEntity(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		// copy driver dto to entity
		User driverEntity = new User();
		BeanUtils.copyProperties(run.getDriver(), driverEntity);
		entity.setDriver(driverEntity);
		// copy vehicle dto to entity
		Vehicle vehicleEntity = new Vehicle();
		BeanUtils.copyProperties(run.getVehicle(), vehicleEntity);
		entity.setVehicle(vehicleEntity);
		// copy subrun entity to dto and assign to run
		List<SubRun> subrunsentity = new ArrayList<>();
		for (SubRunDTO subrun : run.getSubruns()) {
			SubRun subrunEntity = new SubRun();
			BeanUtils.copyProperties(subrun, subrunEntity);
			// copy startPlace
			WayPoint startplaceEntity = new WayPoint();
			BeanUtils.copyProperties(subrun.getStartPlace(), startplaceEntity);
			subrunEntity.setStartPlace(startplaceEntity);
			// copy endPlace
			WayPoint endplaceEntity = new WayPoint();
			BeanUtils.copyProperties(subrun.getEndPlace(), endplaceEntity);
			subrunEntity.setStartPlace(endplaceEntity);
			// copy passengers entity to dto and assign to subrun
			List<Passenger> passengersEntity = new ArrayList<>();
			for (PassengerDTO passenger : subrun.getPassengers()) {
				Passenger passengerEntity = new Passenger();
				BeanUtils.copyProperties(passenger, passengerEntity);
				// copy user entity to dto and assign to passenger
				User userEntity = new User();
				BeanUtils.copyProperties(passenger.getUser(), userEntity);
				passengerEntity.setUser(userEntity);
				// add to list
				passengersEntity.add(passengerEntity);
			}
			subrunEntity.setPassengers(passengersEntity);
			// copy startingPoints
			List<WayPoint> waypointsEntity = new ArrayList<>();
			for (WayPointDTO waypoint : subrun.getStartingPoints()) {
				WayPoint waypointEntity = new WayPoint();
				BeanUtils.copyProperties(waypoint, waypointEntity);
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
			// add subrun to list
			subrunsentity.add(subrunEntity);
		}
		entity.setSubRuns(subrunsentity);
		return entity;
	}

	private RunDTO copyEntityToDto(Run entity) {
		// copy run entity to DTO
		RunDTO run = new RunDTO();
		BeanUtils.copyProperties(entity, run);
		// copy driver entity to DTO and assign to run
		if (entity.getDriver() != null) {
			UserDTO user = new UserDTO();
			BeanUtils.copyProperties(entity.getDriver(), user);
			run.setDriver(user);
		}
		// copy vehicle entity to dto and assign to run
		if (entity.getVehicle() != null) {
			VehicleDTO vehicle = new VehicleDTO();
			BeanUtils.copyProperties(entity.getVehicle(), vehicle);
			run.setVehicle(vehicle);
		}
		// copy subrun entity to dto and assign to run
		List<SubRunDTO> subruns = new ArrayList<>();
		for (SubRun subrunEntity : entity.getSubRuns()) {
			SubRunDTO subrun = new SubRunDTO();
			BeanUtils.copyProperties(subrunEntity, subrun);
			// copy startPlace
			WayPointDTO startPlace = new WayPointDTO();
			BeanUtils.copyProperties(subrunEntity.getStartPlace(), startPlace);
			subrun.setStartPlace(startPlace);
			// copy endPlace
			WayPointDTO endPlace = new WayPointDTO();
			BeanUtils.copyProperties(subrunEntity.getEndPlace(), endPlace);
			subrun.setStartPlace(endPlace);
			// copy passengers entity to dto and assign to subrun
			List<PassengerDTO> passengers = new ArrayList<>();
			for (Passenger passengerEntity : subrunEntity.getPassengers()) {
				PassengerDTO passenger = new PassengerDTO();
				BeanUtils.copyProperties(passengerEntity, passenger);
				// copy user entity to dto and assign to passenger
				UserDTO passengerUser = new UserDTO();
				BeanUtils.copyProperties(passengerEntity.getUser(), passengerUser);
				passenger.setUser(passengerUser);
				passengers.add(passenger);
			}
			subrun.setPassengers(passengers);
			// copy startingPoints
			List<WayPointDTO> startingPoints = new ArrayList<>();
			for (WayPoint waypointEntity : subrunEntity.getStartingPoints()) {
				WayPointDTO startingpoint = new WayPointDTO();
				BeanUtils.copyProperties(waypointEntity, startingpoint);
				startingPoints.add(startingpoint);
			}
			subrun.setStartingPoints(startingPoints);
			// copy startingPoints
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
	public List<RunDTO> findRunsByDriver(UserDTO driver) {
		User driverEntity = new User();
		BeanUtils.copyProperties(driver, driverEntity);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriver(driverEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByDriver(UserDTO driver) {
		User driverEntity = new User();
		BeanUtils.copyProperties(driver, driverEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entities = runRepository.findByDriver(driverEntity);
		// remove cancelled runs
		for (Run entity : entities) {
			if (entity.getSubRuns().get(0).getPassengers().get(0).getReservationState() == ResState.RUN_CANCELED) {
				entities.remove(entity);
			}
		}
		// Copy run entity to dto
		for (Run entity : entities) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByPassenger(UserDTO passenger) {
		User passengerEntity = new User();
		BeanUtils.copyProperties(passenger, passengerEntity);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findBySubRunsPassengersUserExists(passengerEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByPassenger(UserDTO passenger) {
		User passengerEntity = new User();
		BeanUtils.copyProperties(passenger, passengerEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findBySubRunsPassengersUserExists(passengerEntity);
		// remove runs with run_cancelled or canceled by this passenger status
		RunLoop: for (Run run : entity) {
			for (SubRun subrun : run.getSubRuns()) {
				for (Passenger passengerTest : subrun.getPassengers())
					if ((passengerTest.getReservationState() == ResState.RUN_CANCELED)
							|| ((passengerTest.getUser().equals(passengerEntity)
									&& passengerTest.getReservationState() == ResState.CANCELLED))) {
						entity.remove(run);
						continue RunLoop;
					}
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity);
		// remove runs with run_cancelled or canceled by this passenger status
		RunLoop: for (Run run : entity) {
			for (SubRun subrun : run.getSubRuns()) {
				for (Passenger passengerTest : subrun.getPassengers())
					if ((passengerTest.getReservationState() == ResState.RUN_CANCELED)
							|| ((passengerTest.getUser().equals(userEntity)
									&& passengerTest.getReservationState() == ResState.CANCELLED))) {
						entity.remove(run);
						continue RunLoop;
					}
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findCurrentRunsbyUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity);
		// remove runs with endDate < today
		for (Run run : entity) {
			if (run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) {
				entity.remove(run);
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findCurrentRunsNotCancelledByUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity);
		// remove runs with endDate < today and with status run_cancelled or
		// canceled by this passenger
		RunLoop: for (Run run : entity) {
			if (run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) {
				entity.remove(run);
			} else {
				for (SubRun subrun : run.getSubRuns()) {
					for (Passenger passengerTest : subrun.getPassengers())
						if ((passengerTest.getReservationState() == ResState.RUN_CANCELED)
								|| ((passengerTest.getUser().equals(userEntity)
										&& passengerTest.getReservationState() == ResState.CANCELLED))) {
							entity.remove(run);
							continue RunLoop;
						}
				}
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findPassedRunsbyUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity);
		// remove runs with endDate >= today
		for (Run run : entity) {
			if (!run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) {
				entity.remove(run);
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findPassedRunsNotCancelledByUser(UserDTO user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUserExists(userEntity);
		// remove runs with endDate < today and with status run_cancelled or
		// canceled by this passenger
		RunLoop: for (Run run : entity) {
			if (!run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) {
				entity.remove(run);
			} else {
				for (SubRun subrun : run.getSubRuns()) {
					for (Passenger passengerTest : subrun.getPassengers())
						if ((passengerTest.getReservationState() == ResState.RUN_CANCELED)
								|| ((passengerTest.getUser().equals(userEntity)
										&& passengerTest.getReservationState() == ResState.CANCELLED))) {
							entity.remove(run);
							continue RunLoop;
						}
				}
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo,
			String townTo) {
		List<RunDTO> runs = new ArrayList<>();
		// search for runs that have a matching subrun
		List<Run> entity = runRepository
				.findBySubRunsStartingPointsDistrictAndSubRunsStartingPointsTownAndSubRunsStartDateAndSubRunsEndPlaceDistrictAndSubRunsEndPlaceTownAndSubRunsAvailableSeatsGreaterThan(
						districtFrom, townFrom, dateStart, districtTo, townTo, 0);
		// remove runs with endDate < today and with status run_cancelled
		for (Run run : entity) {
			if ((run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) || 
					(run.getSubRuns().get(0).getPassengers().get(0).getReservationState() == ResState.RUN_CANCELED)) {
				entity.remove(run);
			}
		}
		for (Run runEntity : entity) {
			RunDTO run = copyEntityToDto(runEntity);
			runs.add(run);
		}
		return runs;
	}

}
