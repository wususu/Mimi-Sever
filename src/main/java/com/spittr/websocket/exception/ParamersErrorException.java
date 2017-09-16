package com.spittr.websocket.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class ParamersErrorException extends BaseException{

	private String paramer;
	
	public ParamersErrorException() {
		// TODO Auto-generated constructor stub
		super(DataErrorCode);
	}
	
	public ParamersErrorException(String paramer){
		super(DataErrorCode);
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
