package com.spittr.exception;

public abstract class BaseException extends RuntimeException{
	
	private Integer code;

	public BaseException() {
		// TODO Auto-generated constructor stub
	}
	
	public BaseException(Integer code){
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
