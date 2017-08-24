package com.umanteam.dadakar.run.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.run.back.dto.RunPriceDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IRunPriceService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IRunPriceWebService;

@RestController
@RequestMapping("${appli.path}/runprices")
@CrossOrigin(origins="*")
public class RunPriceWebService implements IRunPriceWebService {
	
	@Autowired
	private IRunPriceService runPriceService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public RunPriceDTO add(@RequestBody RunPriceDTO runPriceDTO) { // OK
		return runPriceService.addOrUpdate(runPriceDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public RunPriceDTO update(@RequestBody RunPriceDTO runPriceDTO) { // OK
		return runPriceService.addOrUpdate(runPriceDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) { // OK
		runPriceService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<RunPriceDTO>> findAll() { // OK
		List<RunPriceDTO> runPriceDTOs = runPriceService.findAll();
		if(runPriceDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<RunPriceDTO>>(runPriceDTOs, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public RunPriceDTO findById(@PathVariable("id") String id) { // OK
		return runPriceService.findById(id);
	}

	@RequestMapping(value="/power:{power}", method=RequestMethod.GET)
	@Override
	public RunPriceDTO findByPower(@PathVariable("power") int power) { // OK
		return runPriceService.findByPower(power);
	}

}
