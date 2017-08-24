package com.umanteam.dadakar.run.back.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;

public interface IRunService {

	public RunDTO addRun(RunDTO run);
	public RunDTO updateRun(RunDTO run);
	public void deleteRun(String id);
	public List<RunDTO> findAllRuns();
	public RunDTO findRunsById(String id);
	public List<RunDTO> findRunsByDriver(UserDTO driver);
	public List<RunDTO> findRunsNotCancelledByDriver(UserDTO driver);
	public List<RunDTO> findRunsByPassenger(UserDTO passenger);
	public List<RunDTO> findRunsNotCancelledByPassenger(UserDTO passenger);
	public List<RunDTO> findRunsByUser(UserDTO user);
	public List<RunDTO> findRunsNotCancelledByUser(UserDTO user);
	public List<RunDTO> findCurrentRunsbyUser(UserDTO user);
	public List<RunDTO> findCurrentRunsNotCancelledByUser(UserDTO user);
	public List<RunDTO> findPassedRunsbyUser(UserDTO user);
	public List<RunDTO> findPassedRunsNotCancelledByUser(UserDTO user);
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);
}
