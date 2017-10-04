package com.spittr.websocket.exception;

import com.spittr.config.StatusCodeConf;
import com.spittr.exception.BaseException;

public class NullValueException extends BaseException{

	public NullValueException() {
		// TODO Auto-generated constructor stub
		super(StatusCodeConf.ResourceNotFoundCode);
	}
}
