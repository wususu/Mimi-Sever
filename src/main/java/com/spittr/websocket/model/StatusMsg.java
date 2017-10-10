package com.spittr.websocket.model;

import com.spittr.tools.ExceptionStatus;

/**
 * 状态消息包装类
 * @author janke
 *
 */
public class StatusMsg {

	private String msgID;
	
	private Integer code;
	
	private String status;
	
	private Object content;
	
	public static StatusMsg OK(String msgID, Object content){
		return new StatusMsg(msgID, ExceptionStatus.SUCCESS.getCode(), ExceptionStatus.SUCCESS.getMessage(), content);
	}

	public static StatusMsg OK(String msgID){
		return new StatusMsg(msgID, ExceptionStatus.SUCCESS.getCode(), ExceptionStatus.SUCCESS.getMessage(), ExceptionStatus.DiY_OK_MESSAGE);
	}
	
	public static StatusMsg ERROR(Object content){
		return new StatusMsg(null, ExceptionStatus.ERROR.getCode(), ExceptionStatus.ERROR.getMessage(), content);
	}
	
	public static StatusMsg ERROR(String msgID, Object content){
		return new StatusMsg(msgID, ExceptionStatus.ERROR.getCode(), ExceptionStatus.ERROR.getMessage(), content);
	}
	
	public StatusMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public StatusMsg(String msgID, Integer code, String status, Object content){
		this.setMsgID(msgID);
		this.setCode(code);
		this.setStatus(status);
		this.setContent(content);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
