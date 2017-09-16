package com.spittr.image.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class ImageNotFoundException extends BaseException{

	private String path;
	
	public ImageNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
	
	public ImageNotFoundException(String path) {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
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
