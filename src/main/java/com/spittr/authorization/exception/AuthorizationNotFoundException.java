package com.spittr.authorization.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class AuthorizationNotFoundException extends BaseException{

	private String authName;
	
	public AuthorizationNotFoundException(String authName) {
		// TODO Auto-generated constructor stub
		super(AuthorizationNotFoundCode);
		this.authName = authName;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Require header %s", authName);
	}
}
