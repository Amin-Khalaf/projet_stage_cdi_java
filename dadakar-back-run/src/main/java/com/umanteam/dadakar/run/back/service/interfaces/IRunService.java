package com.umanteam.dadakar.run.back.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;

public interface IRunService {

	public RunDTO addRun(RunDTO run);
	public RunDTO updateRun(RunDTO run);
	public void deleteRun(RunDTO run);
	public List<RunDTO> findAllRuns();
	public RunDTO findRunsById(String id);
	public List<RunDTO> findRunsByDriver(UserDTO driver);
	public List<RunDTO> findRunsByPassenger(UserDTO passenger);
	public List<RunDTO> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);
}
