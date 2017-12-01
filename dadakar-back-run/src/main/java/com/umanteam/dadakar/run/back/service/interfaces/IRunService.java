package com.umanteam.dadakar.run.back.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.umanteam.dadakar.run.back.dto.RunDTO;

public interface IRunService {

	public RunDTO addRun(RunDTO run);
	public RunDTO updateRun(RunDTO run);
	public void deleteRun(String id);
	public List<RunDTO> findAllRuns();
	public RunDTO findRunsById(String id);
	public List<RunDTO> findRunsByDriverId(String driverId);
	public List<RunDTO> findRunsNotCancelledByDriverId(String driverId);
	public List<RunDTO> findRunsByPassengerId(String passengerId);
	public List<RunDTO> findRunsNotCancelledByPassengerId(String passengerId);
	public List<RunDTO> findRunsByUserId(String userId);
	public List<RunDTO> findRunsNotCancelledByUserId(String userId);
	public List<RunDTO> findCurrentRunsByUserId(String userId);
	public List<RunDTO> findCurrentRunsNotCancelledByUserId(String userId);
	public List<RunDTO> findPassedRunsByUserId(String userId);
	public List<RunDTO> findPassedRunsNotCancelledByUserId(String userId);
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);
}
