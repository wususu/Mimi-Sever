package com.spittr.websocket.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class RequiredAuthorizationException extends BaseException{

	public RequiredAuthorizationException() {
		// TODO Auto-generated constructor stub
		super(AuthorityErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"This webSocket connection have not been authorization"
				);
	}
}
