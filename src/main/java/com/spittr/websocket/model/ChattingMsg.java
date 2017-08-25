package com.spittr.websocket.model;

import java.util.Date;

public class ChattingMsg {

	
	private String sender;
	
	private String reciver;
	
	private String content;
	
	private Date time;
	
	public ChattingMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public ChattingMsg(String sender, String reviver, String content){
		this.sender = sender;
		this.reciver = reviver;
		this.content = content;
		this.time = new Date();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ChattingMsg [sender=" + sender + ", reciver=" + reciver + ", content=" + content + ", time=" + time
				+ "]";
	}
	
}
