package com.spittr.image.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class CreateImageErrorException extends BaseException{

	public CreateImageErrorException() {
		// TODO Auto-generated constructor stub
		super(ErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Can not create the image, Please try again."
				);
	}
	
}
