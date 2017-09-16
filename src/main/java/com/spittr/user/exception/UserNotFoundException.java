package com.spittr.user.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class UserNotFoundException extends BaseException{

	private String uname;
	
	public UserNotFoundException(String uname) {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
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
