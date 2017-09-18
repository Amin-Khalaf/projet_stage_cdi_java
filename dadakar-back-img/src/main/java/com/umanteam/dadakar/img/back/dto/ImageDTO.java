package com.umanteam.dadakar.img.back.dto;

import java.io.Serializable;

public class ImageDTO implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = 1947134298024226505L;
	private String name;
	private String image;
	private String type;
	
	public ImageDTO() {}

	public ImageDTO(String name, String image, String type) {
		this.name = name;
		this.image = image;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
