package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.util.List;

import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.run.back.enums.Luggage;

public class RunDTO implements Serializable {

	private static final long serialVersionUID = -5996339903659028184L;
	private String runId;
	private String driverId;
	private VehicleDTO vehicle;
	private List<SubRunDTO> subruns;
	private Luggage luggageType;
	private boolean canceled;

	// Constructors
	public RunDTO() {
		super();
	}

	public RunDTO(String driverId, VehicleDTO vehicle, Luggage luggageType) {
		super();
		this.driverId = driverId;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
		this.canceled = false;
	}

	public RunDTO(String driverId, VehicleDTO vehicle, List<SubRunDTO> subruns, Luggage luggageType) {
		super();
		this.driverId = driverId;
		this.vehicle = vehicle;
		this.subruns = subruns;
		this.luggageType = luggageType;
		this.canceled = false;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public String getDriverId() {
		return driverId;
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

	public boolean isCanceled() {
		return canceled;
	}

	// Setters
	public void setRunId(String runId) {
		this.runId = runId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
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

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	// toString
	@Override
	public String toString() {
		return "RunDTO [runId=" + runId + ", driverId=" + driverId + ", vehicle=" + vehicle + ", subruns=" + subruns
				+ ", luggageType=" + luggageType + ", canceled=" + canceled + "]";
	}

}
