package com.spittr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.model.ReturnModel;
import com.spittr.user.exception.PasswdNotEqualsException;


@ControllerAdvice
public class ExceptionsHandler {
	
	@ExceptionHandler(PasswdNotEqualsException.class)
	public ResponseEntity<ReturnModel> handleTestException(PasswdNotEqualsException e){
		return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(e), HttpStatus.OK);
	}
	
	@ExceptionHandler(TokenErrorException.class)
	public ResponseEntity<ReturnModel> handleTestException2(TokenErrorException e){
		return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(e), HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ReturnModel> handleTestException2(RuntimeException e){
		e.printStackTrace();
		return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(e), HttpStatus.OK);
	}
	
}
