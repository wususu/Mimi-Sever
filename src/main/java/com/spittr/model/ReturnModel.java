package com.spittr.model;

import com.spittr.tools.ExceptionStatus;

public class ReturnModel {

	private int code;
	
	private String message;
	
	private Object content;
	
	public ReturnModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ReturnModel(int code, String message, Object content){
		this.code = code;
		this.message = message;
		this.content = content;
	}
	
	public ReturnModel(ExceptionStatus exceptionStatus, Object content){
		this.code = exceptionStatus.getCode();
		this.message = exceptionStatus.getMessage();
		this.content = content;
	}
	
	public static ReturnModel SUCCESS( Object content){
		return new ReturnModel(ExceptionStatus.SUCCESS, content);
	}
	
	public static ReturnModel SUCCESS(){
		return new ReturnModel(ExceptionStatus.SUCCESS, ExceptionStatus.DiY_OK_MESSAGE);
	}

	public static ReturnModel ERROR(Exception e){
		return new ReturnModel(-500, e.getMessage(), ExceptionStatus.DiY_ERROR_MESSAGE); 
	}
	
	public static ReturnModel ERROR(ExceptionStatus exceptionStatus){
		return new ReturnModel(exceptionStatus, ExceptionStatus.DiY_ERROR_MESSAGE); 
	}
	
	public static ReturnModel ERROR(){
		return new ReturnModel(ExceptionStatus.ERROR, ExceptionStatus.DiY_ERROR_MESSAGE); 
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getContent() {
		return content;
	}
}
