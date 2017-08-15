package com.spittr.image.exception;

public class ImageNotFoundException extends RuntimeException{

	private String path;
	
	public ImageNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ImageNotFoundException(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Can not found Image on directory %s ",
				path
				);
	}
	
}
