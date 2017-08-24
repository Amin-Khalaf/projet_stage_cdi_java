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
	public ResponseEntity<List<RunDTO>> findRunsByDriverId(String userid);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByDriverId(String userid);
	public ResponseEntity<List<RunDTO>> findRunsByPassengerId(String userid);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByPassengerId(String userid);
	public ResponseEntity<List<RunDTO>> findRunsByUserId(String userid);
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByUserId(String userid);
	public ResponseEntity<List<RunDTO>> findCurrentRunsbyUserId(String userid);
	public ResponseEntity<List<RunDTO>> findCurrentRunsNotCancelledByUserId(String userid);
	public ResponseEntity<List<RunDTO>> findPassedRunsbyUserId(String userid);
	public ResponseEntity<List<RunDTO>> findPassedRunsNotCancelledByUserId(String userid);
	public ResponseEntity<List<RunDTO>> findRuns(String districtFrom, String townFrom, LocalDate dateStart, String districtTo, String townTo);

}
