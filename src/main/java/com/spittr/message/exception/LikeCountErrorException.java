package com.spittr.message.exception;

public class LikeCountErrorException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"like count exception"
				);
	}
	
}
