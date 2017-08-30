package com.spittr.websocket.exception;


public class WebSocketBaseException extends RuntimeException{

	private String chatID;
	
	public WebSocketBaseException(String chatID) {
		// TODO Auto-generated constructor stub
		this.chatID = chatID;
	}

	public String getChatID() {
		return chatID;
	}

	public void setChatID(String chatID) {
		this.chatID = chatID;
	}

}
