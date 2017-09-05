package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

public class Message implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = -8528050126373957236L;
	private String msgId;
	private String senderId;
	private String receiverId;
	private String horo;
	private String object;
	private String message;
	
	/* Constructors */
	
	public Message() {}

	public Message(String senderId, String receiverId, String horo, String object, String message) {
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

	public String getHoro() {
		return horo;
	}

	public void setHoro(String horo) {
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

	/* Methods */
	
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", horo=" + horo
				+ ", object=" + object + ", message=" + message + "]";
	}
	
}
