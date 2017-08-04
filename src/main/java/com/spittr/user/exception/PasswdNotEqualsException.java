package com.spittr.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		code = HttpStatus.EXPECTATION_FAILED,
		reason = 	"Passwords are not the same, check again."
		)
public class PasswdNotEqualsException extends RuntimeException{
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Passwords are not the same, check again "
				);
	}
}
