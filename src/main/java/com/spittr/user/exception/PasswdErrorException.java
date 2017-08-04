package com.spittr.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		code = HttpStatus.EXPECTATION_FAILED,
		reason = 	"Password or username error."
		)
public class PasswdErrorException extends RuntimeException{

	public PasswdErrorException() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Password or username error."
				);
	}
	
}
