package com.umanteam.dadakar.run.back.webservice.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IRunWebService;

@RestController
@RequestMapping(value = "${appli.path}/runs")
@CrossOrigin(origins = "*")
public class RunWebService implements IRunWebService {

	@Autowired
	IRunService runService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Override
	public RunDTO addRun(@RequestBody RunDTO run) {
		return runService.addRun(run);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Override
	public RunDTO updateRun(@RequestBody RunDTO run) {
		return runService.updateRun(run);
	}

	@RequestMapping(value = "/delete/{id}")
	@Override
	public void deleteRun(@PathVariable("id") String id) {
		runService.deleteRun(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findAllRuns() {
		List<RunDTO> runs = runService.findAllRuns();
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Override
	public ResponseEntity<RunDTO> findRunsById(@PathVariable("id") String id) {
		RunDTO run = runService.findRunsById(id);
		if (run == null) {
			return new ResponseEntity<RunDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<RunDTO>(run, HttpStatus.OK);
	}

	@RequestMapping(value = "/driver", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsByDriver(@RequestBody UserDTO driver) {
		List<RunDTO> runs = runService.findRunsByDriver(driver);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/driver/notcancelled", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByDriver(@RequestBody UserDTO driver) {
		List<RunDTO> runs = runService.findRunsNotCancelledByDriver(driver);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/passenger", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsByPassenger(@RequestBody UserDTO passenger) {
		List<RunDTO> runs = runService.findRunsByPassenger(passenger);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/passenger/notcancelled", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByPassenger(@RequestBody UserDTO passenger) {
		List<RunDTO> runs = runService.findRunsNotCancelledByPassenger(passenger);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsByUser(@RequestBody UserDTO user) {
		List<RunDTO> runs = runService.findRunsByUser(user);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/notcancelled", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findRunsNotCancelledByUser(@RequestBody UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/user/current", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findCurrentRunsbyUser(@RequestBody UserDTO user) {
		List<RunDTO> runs = runService.findCurrentRunsbyUser(user);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/current/notcanceled", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findCurrentRunsNotCancelledByUser(@RequestBody UserDTO user) {
		List<RunDTO> runs = runService.findCurrentRunsNotCancelledByUser(user);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/passed", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findPassedRunsbyUser(@RequestBody UserDTO user) {
		List<RunDTO> runs = runService.findPassedRunsbyUser(user);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/passed/notcanceled", method = RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunDTO>> findPassedRunsNotCancelledByUser(@RequestBody UserDTO user) {
		List<RunDTO> runs = runService.findPassedRunsNotCancelledByUser(user);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

	@RequestMapping(value="search", method = RequestMethod.GET)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")	@Override
	public ResponseEntity<List<RunDTO>> findRuns(@RequestParam("districtFrom") String districtFrom,
			@RequestParam("townFrom") String townFrom, @RequestParam("dateFrom") LocalDate dateStart,
			@RequestParam("districtTo") String districtTo, @RequestParam("townTo") String townTo) {
		List<RunDTO> runs = runService.findRuns(districtFrom, townFrom, dateStart, districtTo, townTo);
		if (runs == null) {
			return new ResponseEntity<List<RunDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RunDTO>>(runs, HttpStatus.OK);
	}

}
