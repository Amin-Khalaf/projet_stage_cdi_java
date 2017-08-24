package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;

public class SubRun {

	@Id
	private String subRunId;

	public SubRun() {
		super();
	}

	public String getSubRunId() {
		return subRunId;
	}

	public void setSubRunId(String subRunId) {
		this.subRunId = subRunId;
	}

}
