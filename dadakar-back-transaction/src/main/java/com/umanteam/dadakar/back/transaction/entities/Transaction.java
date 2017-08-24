package com.umanteam.dadakar.back.transaction.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.umanteam.dadakar.back.transaction.enums.TxState;

@Entity(name="transaction")
public class Transaction {
	
	/* Variables */
	
	@Id
	@GeneratedValue
	@Column(name="transaction_id")
	private Integer transactionId;
	@Column(name="transaction_number", unique=true)
	private String transactionNumber;
	@Column(name="transaction_date")
	private LocalDateTime transactionDate;
	@Column(name="sender_id")
	private String senderId;
	@Column(name="receiver_id")
	private String receiverId;
	private TxState state;
	
	/* Constructors */
	
	public Transaction() {}

	public Transaction(String transactionNumber, LocalDateTime transactionDate, String senderId, String receiverId, TxState state) {
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
		return "Transaction [transactionId=" + transactionId + ", transactionNumber=" + transactionNumber
				+ ", transactionDate=" + transactionDate + ", senderId=" + senderId + ", receiverId=" + receiverId
				+ ", state=" + state + "]";
	}

}
