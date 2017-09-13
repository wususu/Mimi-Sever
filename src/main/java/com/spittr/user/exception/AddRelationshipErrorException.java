package com.spittr.user.exception;

public class AddRelationshipErrorException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Can't add relationship, talk to the engineer."
				);
	}
}
