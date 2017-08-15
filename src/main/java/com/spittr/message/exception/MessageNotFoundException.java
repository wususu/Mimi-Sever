package com.spittr.message.exception;

public class MessageNotFoundException extends RuntimeException{

	private Long mid;
	
	public MessageNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public MessageNotFoundException(Long mid){
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
