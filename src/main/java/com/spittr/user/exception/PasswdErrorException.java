package com.spittr.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

@ResponseStatus(
		code = HttpStatus.EXPECTATION_FAILED,
		reason = 	"Password or username error."
		)
public class PasswdErrorException extends BaseException{

	public PasswdErrorException() {
		// TODO Auto-generated constructor stub
		super(ErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Password or username error."
				);
	}
	
}
