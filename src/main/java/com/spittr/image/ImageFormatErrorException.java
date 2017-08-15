package com.spittr.image;

public class ImageFormatErrorException extends RuntimeException{

	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Format Base64 to image error."
				);
	}
}
