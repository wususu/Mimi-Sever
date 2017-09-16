package com.spittr.message.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class AuthorityNotAllowException extends BaseException{

	private String uanme;
	
	public AuthorityNotAllowException() {
		// TODO Auto-generated constructor stub
		super(AuthorityErrorCode);
	}
	
	public AuthorityNotAllowException(String uname){
		super(AuthorityErrorCode);
		this.uanme = uname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"User %s have not right to do this action",
				uanme
				);
	}
	
}
