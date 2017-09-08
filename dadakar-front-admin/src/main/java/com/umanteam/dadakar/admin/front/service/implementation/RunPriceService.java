package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.DadakarFrontAdminApplication;
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
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice, headers);
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
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice, headers);
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
		RunPrice runPrice = new RunPrice();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice, headers);
		restTemplate.exchange(url, HttpMethod.DELETE, request, RunPrice.class);
	}

	@Override
	public List<RunPrice> findAll() {
		List<RunPrice> runprices = new ArrayList<>();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<List<RunPrice>> request = new HttpEntity<List<RunPrice>>(runprices, headers);
		ResponseEntity<List<RunPrice>> answer = restTemplate.exchange(runPricePath, HttpMethod.GET, request, new ParameterizedTypeReference<List<RunPrice>>() {});
		List<RunPrice> runPrices = answer.getBody();
		return runPrices;
	}

	@Override
	public RunPrice findById(String id) {
		String url = runPricePath + "/" + id;
		RunPrice runPrice = new RunPrice();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice, headers);
		ResponseEntity<RunPrice> answer = restTemplate.exchange(url, HttpMethod.GET, request, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK){
			return null;
		}
		RunPrice runprice = answer.getBody();
		return runprice;
	}

	@Override
	public RunPrice findByPower(int power) {
		String url = runPricePath + "/power:" + power;
		RunPrice runPrice = new RunPrice();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.tokenValue);
		HttpEntity<RunPrice> request = new HttpEntity<RunPrice>(runPrice, headers);
		ResponseEntity<RunPrice> answer = restTemplate.exchange(url, HttpMethod.GET, request, RunPrice.class);
		if (answer.getStatusCode() != HttpStatus.OK){
			return null;
		}
		RunPrice runprice = answer.getBody();
		return runprice;
	}

}
