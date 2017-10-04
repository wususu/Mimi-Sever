package com.spittr.websocket.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.ResourceNotFoundCode;

public class NtcIDNotFoundException extends BaseException{

	public NtcIDNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
}
