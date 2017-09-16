package com.spittr.exception;

import static com.spittr.config.StatusCodeConf.*;
 
public class LocationNotFoundException extends BaseException{

	public LocationNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Location is not found."
				);
	}
	
}
