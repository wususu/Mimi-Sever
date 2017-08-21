package com.spittr.user.exception;


public class NickNameAlreadyExistException extends RuntimeException{

	private String nname;
	
	public NickNameAlreadyExistException() {
		// TODO Auto-generated constructor stub
	}
	
	public NickNameAlreadyExistException(String nname){
		this.nname = nname;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Nick name %s already exist.",
				this.nname
				);
	}
}
