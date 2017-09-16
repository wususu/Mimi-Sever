package com.spittr.user.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class AddRelationshipErrorException extends BaseException{

	public AddRelationshipErrorException() {
		// TODO Auto-generated constructor stub
		super(ErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Can't add relationship, talk to the engineer."
				);
	}
}
