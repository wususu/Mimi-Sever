package com.spittr.message.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class UnderMessageNotEqualException extends BaseException{

	public UnderMessageNotEqualException() {
		// TODO Auto-generated constructor stub
		super(AuthorityErrorCode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Comment and ReplayComment are not under the same Message."
				);
	}
	
}
