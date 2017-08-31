package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

public class Rating implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -1007143092687268862L;
	private String ratingId;
	private int value;
	private User rater;
	private String comment;
	
	/* Constructors */
	
	public Rating() {
	}

	public Rating(int value, User rater, String comment) {
		this.value = value;
		this.rater = rater;
		this.comment = comment;
	}

	public Rating(String ratingId, int value, User rater, String comment) {
		this.ratingId = ratingId;
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

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
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
		return "Rating [ratingId=" + ratingId + ", value=" + value + ", rater=" + rater + ", comment=" + comment + "]";
	}

}
