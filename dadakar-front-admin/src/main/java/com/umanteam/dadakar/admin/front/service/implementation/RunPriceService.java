package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.RunPrice;
import com.umanteam.dadakar.admin.front.service.interfaces.IRunPriceService;

@Service
public class RunPriceService implements IRunPriceService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${runprice.path}")
	private String runPricePath;

	@Override
	public RunPrice add(RunPrice runPrice) {
		String url = runPricePath + "/save";
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice);
		ResponseEntity<RunPrice> answer = restTemplate.exchange(url, HttpMethod.POST, request, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		runPrice = answer.getBody();
		return runPrice;
	}

	@Override
	public RunPrice update(RunPrice runPrice) {
		String url = runPricePath + "/update";
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice);
		ResponseEntity<RunPrice> answer = restTemplate.exchange(url, HttpMethod.PUT, request, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		runPrice = answer.getBody();
		return runPrice;
	}

	@Override
	public void delete(String id) {
		String url = runPricePath + "/del/" + id;
		restTemplate.delete(url);
	}

	@Override
	public List<RunPrice> findAll() {
		ResponseEntity<List<RunPrice>> answer = restTemplate.exchange(runPricePath, HttpMethod.GET, null, new ParameterizedTypeReference<List<RunPrice>>() {
		});
		List<RunPrice> runPrices = answer.getBody();
		return runPrices;
	}

	@Override
	public RunPrice findById(String id) {
		String url = runPricePath + "/" + id;
		ResponseEntity<RunPrice> answer = restTemplate.getForEntity(url, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK){
			return null;
		}
		RunPrice runprice = answer.getBody();
		return runprice;
	}

	@Override
	public RunPrice findByPower(int power) {
		String url = runPricePath + "/power:" + power;
		ResponseEntity<RunPrice> answer = restTemplate.getForEntity(url, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK){
			return null;
		}
		RunPrice runprice = answer.getBody();
		return runprice;
	}

}
