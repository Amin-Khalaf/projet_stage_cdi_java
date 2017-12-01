package com.umanteam.dadakar.admin.front.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.admin.front.dto.RunPrice;

public interface IRunPriceService {
	
	RunPrice add(RunPrice runPrice);
	RunPrice update(RunPrice runPrice);
	void delete(String id);
	List<RunPrice> findAll();
	RunPrice findById(String id);
	RunPrice findByPower(int power);

}
