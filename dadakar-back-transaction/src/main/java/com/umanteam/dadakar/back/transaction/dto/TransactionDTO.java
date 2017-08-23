package com.umanteam.dadakar.back.transaction.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umanteam.dadakar.back.transaction.enums.TxState;

public class TransactionDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 5853914459493650465L;
	private Integer transactionId;
	private String transactionNumber;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime transactionDate;
	private String senderId;
	private String receiverId;
	private TxState state;
	
	/* Constructors */
	
	public TransactionDTO() {}

	public TransactionDTO(String transactionNumber, LocalDateTime transactionDate, String senderId, String receiverId, TxState state) {
		this.transactionNumber = transactionNumber;
		this.transactionDate = transactionDate;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.state = state;
	}

	/* Getters and Setters */
	
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public TxState getState() {
		return state;
	}

	public void setState(TxState state) {
		this.state = state;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "TransactionDTO [transactionId=" + transactionId + ", transactionNumber=" + transactionNumber
				+ ", transactionDate=" + transactionDate + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", state=" + state + "]";
	}
	
}
