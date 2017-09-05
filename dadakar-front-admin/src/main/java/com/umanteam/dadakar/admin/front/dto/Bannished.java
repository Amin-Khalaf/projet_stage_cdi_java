package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;
import java.util.List;

public class Bannished implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -5627048744525047973L;
	private List<String> banned;
	
	/* Getters and Setters */
	
	public List<String> getBanned() {
		return banned;
	}

	public void setBanned(List<String> banned) {
		this.banned = banned;
	}
	
}
