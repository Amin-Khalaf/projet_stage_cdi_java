package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.util.List;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.run.back.enums.Luggage;

public class RunDTO implements Serializable {

	private static final long serialVersionUID = 7867532309907390114L;
	private String runId;
	private UserDTO driver;
	private VehicleDTO vehicle;
	private List<SubRunDTO> subruns;
	private Luggage luggageType;
	
	// Constructors
	public RunDTO() {
		super();
	}

	public RunDTO(UserDTO driver, VehicleDTO vehicle, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
	}

	public RunDTO(UserDTO driver, VehicleDTO vehicle, List<SubRunDTO> subruns, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.subruns = subruns;
		this.luggageType = luggageType;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public UserDTO getDriver() {
		return driver;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public List<SubRunDTO> getSubruns() {
		return subruns;
	}

	public Luggage getLuggageType() {
		return luggageType;
	}

	// Setters
	public void setRunId(String runId) {
		this.runId = runId;
	}

	public void setDriver(UserDTO driver) {
		this.driver = driver;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public void setSubruns(List<SubRunDTO> subruns) {
		this.subruns = subruns;
	}

	public void setLuggageType(Luggage luggageType) {
		this.luggageType = luggageType;
	}

	// toString
	@Override
	public String toString() {
		return "RunDTO [runId=" + runId + ", driver=" + driver + ", vehicle=" + vehicle + ", subruns=" + subruns + ", luggageType=" + luggageType + "]";
	}
	
}
