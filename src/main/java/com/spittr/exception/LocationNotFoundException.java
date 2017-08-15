package com.spittr.exception;

public class LocationNotFoundException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Location is not found."
				);
	}
	
}
