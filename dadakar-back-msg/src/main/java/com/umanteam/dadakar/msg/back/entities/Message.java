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
	private String object;
	private String message;
	private boolean seen;
	
	/* Constructors */
	
	public Message() {}

	public Message(String senderId, String receiverId, LocalDateTime horo, String object, String message) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.horo = horo;
		this.object = object;
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

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", horo=" + horo
				+ ", object=" + object + ", message=" + message + "]";
	}

}
