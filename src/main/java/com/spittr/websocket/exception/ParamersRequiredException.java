package com.spittr.websocket.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class ParamersRequiredException extends BaseException{

	private String paramer;
	
	public ParamersRequiredException() {
		// TODO Auto-generated constructor stub
		super(DataErrorCode);
	}
	
	public ParamersRequiredException(String paramer){
		super(DataErrorCode);
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
