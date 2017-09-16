package com.spittr.message.exception;

import com.spittr.exception.BaseException;

import static com.spittr.config.StatusCodeConf.*;

public class CommentNotFoundException extends BaseException{

	private Long cid;
	
	public CommentNotFoundException() {
		// TODO Auto-generated constructor stub
		super(ResourceNotFoundCode);
	}
	
	public CommentNotFoundException(Long cid){
		super(ResourceNotFoundCode);
		this.cid = cid;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return String.format(
				"Comment %d is not found.",
				cid
				);
	}
	
}
