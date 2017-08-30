package com.spittr.websocket.exception;

public class RequiredAuthorizationException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"This webSocket connection have not been authorization"
				);
	}
}
