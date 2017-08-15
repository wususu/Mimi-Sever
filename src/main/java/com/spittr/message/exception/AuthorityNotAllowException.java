package com.spittr.message.exception;

import com.spittr.user.model.User;

public class AuthorityNotAllowException extends RuntimeException{

	private String uanme;
	
	public AuthorityNotAllowException() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthorityNotAllowException(String uname){
		this.uanme = uname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"User %s have not right to delete the message",
				uanme
				);
	}
	
}
