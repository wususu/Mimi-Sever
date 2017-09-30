package com.spittr.authorization.exception;

import com.spittr.exception.BaseException;
import static com.spittr.config.StatusCodeConf.*;

public class ElementNotFoundException extends BaseException{
	
	public ElementNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"HmtUser infomation not found."
				);
	}

}
