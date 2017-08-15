package com.spittr.image.exception;

public class ImageAlreadyBeUsedException extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"The image already be used, please create another one"
				);
	}
	
}
