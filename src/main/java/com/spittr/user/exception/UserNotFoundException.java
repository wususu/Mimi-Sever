package com.spittr.user.exception;

public class UserNotFoundException extends RuntimeException{

	private String uname;
	
	public UserNotFoundException(String uname) {
		// TODO Auto-generated constructor stub
		this.uname = uname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"User not found: %s", uname
				);
	}
}
