package com.spittr.user.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class UserNameAlreadyExistException extends BaseException{

	private String uname;
	
	public UserNameAlreadyExistException() {
		// TODO Auto-generated constructor stub
		super(ForbiddenCode);
	}
	
	public UserNameAlreadyExistException(String uname){
		super(ForbiddenCode);
		this.uname = uname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"User name %s already exist",
				this.uname
				);
	}
}
