package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

public class RatingDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = -4770346576478780702L;
	private String ratingId;
	private int value;
	private UserDTO rater;
	private String comment;
	
	/* Constructor */
	
	public RatingDTO() {}

	public RatingDTO(int value, UserDTO rater, String comment) {
		this.value = value;
		this.rater = rater;
		this.comment = comment;
	}
	
	/* Getters and Setters */

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public UserDTO getRater() {
		return rater;
	}

	public void setRater(UserDTO rater) {
		this.rater = rater;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "RatingDTO [ratingId=" + ratingId + ", value=" + value + ", comment=" + comment + "]";
	}
	
}
