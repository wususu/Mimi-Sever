package com.spittr.websocket.model;


public class ReciveMsg {
	
	private String msgID;
	
	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	@Override
	public String toString() {
		return "ReciveMsg [msgID=" + msgID + "]";
	}
	
	
	
}
