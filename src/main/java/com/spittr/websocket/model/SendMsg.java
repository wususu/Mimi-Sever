package com.spittr.websocket.model;

import java.util.Date;

public class SendMsg {
	
	private String msgID;
		
	private Long reciverId;
	
	private String message;
	
	public SendMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public SendMsg(String msgID, Long reciverId, String message, Date time){
		this.msgID = msgID;
		this.reciverId = reciverId;
		this.message = message;
	}


	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public Long getReciverId() {
		return reciverId;
	}

	public void setReciverId(Long reciverId) {
		this.reciverId = reciverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChattingMsg [msgID=" + msgID + ", reciver=" + reciverId + ", message=" + message + "]";
	}
	
}
