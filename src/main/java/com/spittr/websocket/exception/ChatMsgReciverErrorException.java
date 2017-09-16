package com.spittr.websocket.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class ChatMsgReciverErrorException extends BaseException{

	public ChatMsgReciverErrorException() {
		// TODO Auto-generated constructor stub
		super(ForbiddenCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Reciver error, check again"
				);
	}
	
}
