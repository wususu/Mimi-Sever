package com.spittr.authorization.exception;


public class TokenErrorException extends RuntimeException{
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Token error, check it again."
				);
	}
}
