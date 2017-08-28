package com.umanteam.dadakar.back.full.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.dto.RunDTO;

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
	public List<RunDTO> findCurrentRunsByUser(UserDTO user);
	public List<RunDTO> findCurrentRunsNotCancelledByUser(UserDTO user);
	public List<RunDTO> findPassedRunsByUser(UserDTO user);
	public List<RunDTO> findPassedRunsNotCancelledByUser(UserDTO user);
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);
}
