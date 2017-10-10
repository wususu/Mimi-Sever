package com.spittr.config;


public class StaticConfig {
	
	private StaticConfig(){
		throw new AssertionError();
	}

	public static final Integer ITEM_PER_PAGE = 15;
	
	public static final String DEFAULT_IMAGE_DIRECTORY = "/images";
	
	public static final Long Token_EXPIRED_TIME = 720000000L;
	
	public static final String WEBSOCKET_WEB_PARAM = "srect";
	
	public static final String HEAD_AUTHORIZATION = "janke-authorization";
	
	public static final String USER_ID = "UID";

}
