package com.spittr.websocket.exception;

public class ChatMsgReciverErrorException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Reciver error, check again"
				);
	}
	
}
