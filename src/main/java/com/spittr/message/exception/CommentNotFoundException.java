package com.spittr.message.exception;

public class CommentNotFoundException extends RuntimeException{

	private Long cid;
	
	public CommentNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public CommentNotFoundException(Long cid){
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
