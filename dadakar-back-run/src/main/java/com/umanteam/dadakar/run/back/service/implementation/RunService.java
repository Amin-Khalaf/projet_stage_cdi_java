package com.umanteam.dadakar.run.back.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.RatingDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.Rating;
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
	
		private Run copyDtoToEntity(RunDTO run) {
		Run entity = new Run();
		BeanUtils.copyProperties(run, entity);
		// copy driver dto to entity
		User driverEntity = copyUserDTOtoEntity(run.getDriver());
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
			// save waypoint if doesn't exists in DB
			if (startplaceEntity.getId() == null || waypointRepository.findOne(startplaceEntity.getId()) == null) {
				startplaceEntity = waypointRepository.save(startplaceEntity);
			}
			// assign it to subrun
			subrunEntity.setStartPlace(startplaceEntity);
			// copy endPlace
			WayPoint endplaceEntity = new WayPoint();
			BeanUtils.copyProperties(subrun.getEndPlace(), endplaceEntity);
			// save waypoint if doesn't exists in DB
			if (endplaceEntity.getId() == null || waypointRepository.findOne(endplaceEntity.getId()) == null) {
				endplaceEntity = waypointRepository.save(endplaceEntity);
			}
			// assign it to subrun
			subrunEntity.setEndPlace(endplaceEntity);
			// copy passengers entity to dto and assign to subrun
			if (subrun.getPassengers() != null) {
				List<Passenger> passengersEntity = new ArrayList<>();
				for (PassengerDTO passenger : subrun.getPassengers()) {
					Passenger passengerEntity = new Passenger();
					BeanUtils.copyProperties(passenger, passengerEntity);
					// copy user entity to dto and assign to passenger
					User userEntity = new User();
					BeanUtils.copyProperties(passenger.getUser(), userEntity);
					passengerEntity.setUser(userEntity);
					// save passenger if doesn't exist
					if (passengerEntity.getPassengerId() == null || passengerRepository.findOne(passenger.getPassengerId()) == null) {
						passengerEntity = passengerRepository.save(passengerEntity);
					}
					// add to list
					passengersEntity.add(passengerEntity);
				}
				subrunEntity.setPassengers(passengersEntity);
			}
			// copy startingPoints
			List<WayPoint> waypointsEntity = new ArrayList<>();
			for (WayPointDTO waypoint : subrun.getStartingPoints()) {
				WayPoint waypointEntity = new WayPoint();
				BeanUtils.copyProperties(waypoint, waypointEntity);
				// save waypoint if doesn't exists
				if (waypointEntity.getId() == null || waypointRepository.findOne(waypointEntity.getId()) == null) {
					waypointEntity = waypointRepository.save(waypointEntity);
				}
				waypointsEntity.add(waypointEntity);
			}
			subrunEntity.setStartingPoints(waypointsEntity);
			// copy tolls
			if (subrun.getTolls() != null) {
				List<Toll> tollsEntity = new ArrayList<>();
				for (TollDTO toll : subrun.getTolls()) {
					Toll tollEntity = new Toll();
					BeanUtils.copyProperties(toll, tollEntity);
					// TODO : save toll if doesn't exists
					tollsEntity.add(tollEntity);
				}
				subrunEntity.setTolls(tollsEntity);
			}
			// save subrun if doesn't exists
			if (subrunEntity.getSubRunId() == null || subrunRepository.findOne(subrunEntity.getSubRunId()) == null) {
				subrunRepository.save(subrunEntity);
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
			UserDTO user = copyUserEntityToDto(entity.getDriver());
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
			subrun.setEndPlace(endPlace);
			// copy passengers entity to dto and assign to subrun
			if (subrunEntity.getPassengers() != null) {
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
			}
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
	
	private User copyUserDTOtoEntity(UserDTO user){
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		Account account = new Account();
		BeanUtils.copyProperties(user.getAccount(), account);
		userEntity.setAccount(account);
		if (user.getVehicles() != null){
			List<Vehicle> vehicles = new ArrayList<>();
			for (VehicleDTO vehicleDTO : user.getVehicles()){
				Vehicle vehicle = new Vehicle();
				BeanUtils.copyProperties(vehicleDTO, vehicle);
				vehicles.add(vehicle);
			}
			userEntity.setVehicles(vehicles);
		}
		if (user.getRatings() != null) {
			List<Rating> ratings = new ArrayList<>();
			for(RatingDTO ratingDTO: user.getRatings()) {
				Rating rating = new Rating();
				BeanUtils.copyProperties(ratingDTO, rating);
				ratings.add(rating);
			}
			userEntity.setRatings(ratings);
		}
		return userEntity;
	}

	private UserDTO copyUserEntityToDto(User entity){
		UserDTO user = new UserDTO();
		BeanUtils.copyProperties(entity, user);
		AccountDTO account = new AccountDTO();
		BeanUtils.copyProperties(entity.getAccount(), account);
		user.setAccount(account);
		if (entity.getVehicles() != null){
			List<VehicleDTO> vehicles = new ArrayList<>();
			for (Vehicle vehicle : entity.getVehicles()){
				VehicleDTO vehicledto = new VehicleDTO();
				BeanUtils.copyProperties(vehicle, vehicledto);
				vehicles.add(vehicledto);
			}
			user.setVehicles(vehicles);
		}
		if (entity.getRatings() != null) {
			List<RatingDTO> ratings = new ArrayList<>();
			for(Rating rating: entity.getRatings()) {
				RatingDTO ratingDTO = new RatingDTO();
				BeanUtils.copyProperties(rating, ratingDTO);
				ratings.add(ratingDTO);
			}
			user.setRatings(ratings);
		}
		return user;
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
		User driverEntity = copyUserDTOtoEntity(driver);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriver(driverEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsByDriverUserId(String userid) {
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriverUserId(userid)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByDriver(UserDTO driver) {
		User driverEntity = copyUserDTOtoEntity(driver);
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
		User passengerEntity = copyUserDTOtoEntity(passenger);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findBySubRunsPassengersUser(passengerEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByPassenger(UserDTO passenger) {
		User passengerEntity = copyUserDTOtoEntity(passenger);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findBySubRunsPassengersUser(passengerEntity);
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
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		for (Run entity : runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity)) {
			RunDTO run = copyEntityToDto(entity);
			runs.add(run);
		}
		return runs;
	}

	@Override
	public List<RunDTO> findRunsNotCancelledByUser(UserDTO user) {
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity);
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
	public List<RunDTO> findCurrentRunsByUser(UserDTO user) {
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity);
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
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity);
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
	public List<RunDTO> findPassedRunsByUser(UserDTO user) {
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity);
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
		User userEntity = copyUserDTOtoEntity(user);
		List<RunDTO> runs = new ArrayList<>();
		List<Run> entity = runRepository.findByDriverOrSubRunsPassengersUser(userEntity, userEntity);
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
			if ((run.getSubRuns().get(0).getEstimatedEndDate().isBefore(LocalDate.now())) || (run.getSubRuns().get(0)
					.getPassengers().get(0).getReservationState() == ResState.RUN_CANCELED)) {
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
