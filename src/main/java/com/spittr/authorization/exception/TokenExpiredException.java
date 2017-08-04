package com.spittr.authorization.exception;


public class TokenExpiredException extends TokenErrorException{

	private String uname;
	
	public TokenExpiredException(String uname) {
		// TODO Auto-generated constructor stub
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
