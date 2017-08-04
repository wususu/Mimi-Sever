package com.spittr.authorization.exception;


public class AuthorizationNotFoundException extends TokenErrorException{

	private String authName;
	
	public AuthorizationNotFoundException(String authName) {
		// TODO Auto-generated constructor stub
		this.authName = authName;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Require header %s", authName);
	}
}
