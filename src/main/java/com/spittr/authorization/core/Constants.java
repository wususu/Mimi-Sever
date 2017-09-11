package com.spittr.authorization.core;
import static com.spittr.config.StaticConfig.*;


public class Constants {
	
	private Constants(){
		throw new AssertionError();
 	}
	
	public static final String AUTHORIZATION =  HEAD_AUTHORIZATION;
	
	public static final String CURRENT_USER_ID = USER_ID;
}
