package com.spittr.authorization.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class TokenExpiredException extends BaseException{

	private String uname;
	
	public TokenExpiredException() {
		// TODO Auto-generated constructor stub
		super(TokenExpiredCode);
	}
	
	public TokenExpiredException(String uname) {
		// TODO Auto-generated constructor stub
		super(TokenExpiredCode);
		this.uname = uname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"User %s's token is expired, login again .",
				uname
				);
	}
}
