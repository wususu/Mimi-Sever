package com.spittr.websocket.model;

import java.util.Date;

public class SendMsg {
	
	private Long senderId;
	
	private Long reciverId;
	
	private String content;
	
	public SendMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public SendMsg(Long senderId, Long reciverId, String content, Date time){
		this.senderId = senderId;
		this.reciverId = reciverId;
		this.content = content;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReciverId() {
		return reciverId;
	}

	public void setReciverId(Long reciverId) {
		this.reciverId = reciverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ChattingMsg [sender=" + senderId + ", reciver=" + reciverId + ", content=" + content + "]";
	}
	
}
