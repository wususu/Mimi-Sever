package com.spittr.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

@ResponseStatus(
		code = HttpStatus.EXPECTATION_FAILED,
		reason = 	"Passwords are not the same, check again."
		)
public class PasswdNotEqualsException extends BaseException{
	
	public PasswdNotEqualsException() {
		// TODO Auto-generated constructor stub
		super(DataErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Passwords are not the same, check again "
				);
	}
}
