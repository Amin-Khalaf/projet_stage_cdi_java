package com.umanteam.dadakar.msg.back.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = -9214186791586058825L;
	private String msgId;
	private String senderId;
	private String receiverId;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd_HH:mm")
	private LocalDateTime horo;
	private String message;
	
	/* Constructors */
	
	public MessageDTO() {}

	public MessageDTO(String senderId, String receiverId, LocalDateTime horo, String message) {
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
		return "MessageDTO [msgId=" + msgId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", horo=" + horo
				+ ", message=" + message + "]";
	}
	
}
