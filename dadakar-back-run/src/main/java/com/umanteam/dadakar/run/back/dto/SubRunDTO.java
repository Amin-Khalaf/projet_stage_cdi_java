package com.umanteam.dadakar.run.back.dto;

import org.springframework.data.annotation.Id;

public class SubRunDTO {

	@Id
	private String subRunId;

	public SubRunDTO() {
		super();
	}

	public String getSubRunId() {
		return subRunId;
	}

	public void setSubRunId(String subRunId) {
		this.subRunId = subRunId;
	}
	
	
}
