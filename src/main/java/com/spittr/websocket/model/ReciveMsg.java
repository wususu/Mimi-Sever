package com.spittr.websocket.model;


public class ReciveMsg {
	
	private Long chatId;
	
	private Long reciverId;
	
	private String reciver;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getReciverId() {
		return reciverId;
	}

	public void setReciverId(Long reciverId) {
		this.reciverId = reciverId;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	@Override
	public String toString() {
		return "ReciveMsg [chatId=" + chatId + ", reciverId=" + reciverId + ", reciver=" + reciver + "]";
	}
	
	
	
}
