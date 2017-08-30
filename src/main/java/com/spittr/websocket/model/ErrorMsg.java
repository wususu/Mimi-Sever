package com.spittr.websocket.model;

import org.springframework.messaging.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"msg"})
public class ErrorMsg {
	
	private String error;
	
	private String message;
	
	private Message<Object> msg;
	
	public static ErrorMsg newInstance(Exception e, Message<Object> msg){
		return new ErrorMsg(e, msg);
	}
	
	public ErrorMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public ErrorMsg(String error, String message, Message<Object> msg){
		this.error = error;
		this.message = message;
		this.msg = msg;
	}
	
	public  ErrorMsg(Exception exception, Message<Object> msg ) {
		this(exception.getClass().getSimpleName(), exception.getMessage(), msg);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message<Object> getMsg() {
		return msg;
	}

	public void setMsg(Message<Object> msg) {
		this.msg = msg;
	}

}
