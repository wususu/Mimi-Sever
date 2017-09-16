package com.spittr.authorization.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class TokenErrorException extends BaseException{
	
	public TokenErrorException() {
		// TODO Auto-generated constructor stub
		super(TokenErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Token error, check it again."
				);
	}
}
