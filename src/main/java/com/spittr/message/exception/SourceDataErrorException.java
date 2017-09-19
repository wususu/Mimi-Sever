package com.spittr.message.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class SourceDataErrorException extends BaseException{

	public SourceDataErrorException() {
		// TODO Auto-generated constructor stub
		super(DataErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
}
