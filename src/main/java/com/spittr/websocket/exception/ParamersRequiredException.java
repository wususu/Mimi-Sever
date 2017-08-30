package com.spittr.websocket.exception;

import com.spittr.websocket.model.SendMsg;

public class ParamersRequiredException extends RuntimeException{

	private String paramer;
	
	public ParamersRequiredException() {
		// TODO Auto-generated constructor stub
	}
	
	public ParamersRequiredException(String paramer){
		this.paramer = paramer;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return 	String.format(
				"Paramers %s is required with a sending message",
				paramer
				);
	}
}
