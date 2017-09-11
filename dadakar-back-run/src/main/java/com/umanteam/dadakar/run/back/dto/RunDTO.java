package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.util.List;

import com.umanteam.dadakar.run.back.enums.Luggage;

public class RunDTO implements Serializable {

	private static final long serialVersionUID = -3915999188788680506L;
	private String runId;
	private UserDTO driver;
	private String vehicleId;
	private List<SubRunDTO> subruns;
	private Luggage luggageType;
	private boolean cancelled;

	// Constructors
	public RunDTO() {
		super();
	}

	public RunDTO(UserDTO driver, String vehicleId, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicleId = vehicleId;
		this.luggageType = luggageType;
		this.cancelled = false;
	}

	public RunDTO(UserDTO driver, String vehicleId, List<SubRunDTO> subruns, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicleId = vehicleId;
		this.subruns = subruns;
		this.luggageType = luggageType;
		this.cancelled = false;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public UserDTO getDriver() {
		return driver;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public List<SubRunDTO> getSubruns() {
		return subruns;
	}

	public Luggage getLuggageType() {
		return luggageType;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	// Setters
	public void setRunId(String runId) {
		this.runId = runId;
	}

	public void setDriver(UserDTO driver) {
		this.driver = driver;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setSubruns(List<SubRunDTO> subruns) {
		this.subruns = subruns;
	}

	public void setLuggageType(Luggage luggageType) {
		this.luggageType = luggageType;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	// toString
	@Override
	public String toString() {
		return "RunDTO [runId=" + runId + ", driver=" + driver + ", vehicleId=" + vehicleId + ", subruns=" + subruns
				+ ", luggageType=" + luggageType + ", cancelled=" + cancelled + "]";
	}

}
