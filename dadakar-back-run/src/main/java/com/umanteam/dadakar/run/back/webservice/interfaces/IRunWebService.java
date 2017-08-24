package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;

public interface IRunWebService {

	public RunDTO addRun(RunDTO run);
	public RunDTO updateRun(RunDTO run);
	public void deleteRun(String id);
	public ResponseEntity<List<RunDTO>> findAllRuns();
	public ResponseEntity<RunDTO> findRunsById(String id);
	public ResponseEntity<List<RunDTO>> findRunsByDriver(UserDTO driver);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByDriver(UserDTO driver);
	public ResponseEntity<List<RunDTO>> findRunsByPassenger(UserDTO passenger);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByPassenger(UserDTO passenger);
	public ResponseEntity<List<RunDTO>> findRunsByUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findCurrentRunsbyUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findCurrentRunsNotCancelledByUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findPassedRunsbyUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findPassedRunsNotCancelledByUser(UserDTO user);
	public ResponseEntity<List<RunDTO>> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);

}
