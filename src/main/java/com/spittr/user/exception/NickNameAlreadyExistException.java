package com.spittr.user.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class NickNameAlreadyExistException extends BaseException{

	private String nname;
	
	public NickNameAlreadyExistException() {
		// TODO Auto-generated constructor stub
		super(ForbiddenCode);
	}
	
	public NickNameAlreadyExistException(String nname){
		super(ForbiddenCode);
		this.nname = nname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Nick name %s already exist.",
				this.nname
				);
	}
}
