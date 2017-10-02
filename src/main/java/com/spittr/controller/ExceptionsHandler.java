package com.spittr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.authorization.exception.TokenExpiredException;
import com.spittr.exception.BaseException;
import com.spittr.model.ReturnModel;
import com.spittr.user.exception.PasswdNotEqualsException;


@ControllerAdvice
@CrossOrigin(origins="*", maxAge=3600)
public class ExceptionsHandler {
	
	protected Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);
	
	/**
	 * 操作错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ReturnModel> handleException(BaseException e){
		return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(e), HttpStatus.OK);
	}
	
	/**
	 * 系统错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ReturnModel> handleException(Exception e){
		logger.info(e.toString());
		return new ResponseEntity<ReturnModel>(ReturnModel.ERROR(), HttpStatus.OK);
	}
	
}
