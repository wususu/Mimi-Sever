package com.spittr.core;

import java.util.HashMap;
import java.util.Map;

public class JSONConstants {

	public static final String MESSAGE_LIST = "messageList";
	
	public static final String COMMENT_LIST = "commentList";
	
	public static final String MESSAGE = "message";
	
	public static final String PAGE = "page";
	
	public static Map<String, Object> getMap(){
		return new HashMap<String, Object>();
	}
}
