package com.spittr.user.exception;

public class UserNameAlreadyExistException extends RuntimeException{

	private String uname;
	
	public UserNameAlreadyExistException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNameAlreadyExistException(String uname){
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
