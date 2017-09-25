package com.spittr.core;

import java.util.*;

public class JSONConstants {

	private JSONConstants(){
		throw new AssertionError();
	}
	
	public static final String MESSAGE_LIST = "messageList";
	
	public static final String COMMENT_LIST = "commentList";
	
	public static final String LOCATION_LIST = "locationList";
	
	public static final String OBJECT_USER_LIST = "objectUserList";
	
	public static final String MAIN_USER_LIST = "mainUserList";
	
	public static final String MESSAGE = "message";
	
	public static final String PAGE = "page";
	
	public static final String NUM_PER_PAGE = "numPerPage";
	
	public static final String NUM_THIS_PAGE = "numThisPage";
	
	public static final String BEFORE_TIME = "tmBefore";
	
	public static final String AFTER_TIME = "tmAfter";
	
	public static final String ISLIKE = "isLike";
	
	public static final String LIKE_COUNT = "likeCount";
	
	public static final String USER = "user";
	
	public static final String IS_ATTENTION = "isAttention";

	public static final String ATTENTION_TIME = "tmAttention";
	
	
	public static Map<String, Object> getMap(){
		return new HashMap<String, Object>();
	}
}
