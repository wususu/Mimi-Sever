package com.spittr.websocket.exception;


public class StompHeaderRequiredException extends RuntimeException{

	private String header;
	
	public StompHeaderRequiredException(String header) {
		// TODO Auto-generated constructor stub
		this.header = header;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Require custom header %s", 
				header
				);
	}
}
