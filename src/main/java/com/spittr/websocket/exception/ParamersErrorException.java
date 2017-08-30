package com.spittr.websocket.exception;

public class ParamersErrorException extends RuntimeException{

	private String paramer;
	
	public ParamersErrorException() {
		// TODO Auto-generated constructor stub
	}
	
	public ParamersErrorException(String paramer){
		this.paramer = paramer;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Paramer %s error, check it again",
				paramer
				);
	}
	
}
