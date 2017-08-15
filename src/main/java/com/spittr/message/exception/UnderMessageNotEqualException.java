package com.spittr.message.exception;

public class UnderMessageNotEqualException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Comment and ReplayComment are not under the same Message."
				);
	}
	
}
