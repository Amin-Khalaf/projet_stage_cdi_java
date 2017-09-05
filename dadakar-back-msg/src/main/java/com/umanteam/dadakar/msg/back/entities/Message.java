package com.umanteam.dadakar.msg.back.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="messages")
public class Message {
	
	/* Variables */
	
	@Id
	private String msgId;
	private String senderId;
	private String receiverId;
	private LocalDateTime horo;
	private String message;
	
	/* Constructors */
	
	public Message() {}

	public Message(String senderId, String receiverId, LocalDateTime horo, String message) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.horo = horo;
		this.message = message;
	}

	/* Getters and Setters */
	
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

	public LocalDateTime getHoro() {
		return horo;
	}

	public void setHoro(LocalDateTime horo) {
		this.horo = horo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", horo=" + horo
				+ ", message=" + message + "]";
	}

}
