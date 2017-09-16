package com.spittr.message.exception;

import static com.spittr.config.StatusCodeConf.*;

import com.spittr.exception.BaseException;

public class MessageNotFoundException extends BaseException{

	private Long mid;
	
	public MessageNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
	
	public MessageNotFoundException(Long mid){
		super(ResourceNotFoundCode);
		this.mid = mid;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Message %d not found" ,
				mid);
	}
	
}
