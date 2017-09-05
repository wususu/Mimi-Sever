package com.spittr.websocket.model;

/**
 * 聊天消息确认接收发送实体
 * @author janke
 *
 */
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
