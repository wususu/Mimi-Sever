package com.spittr.filter;

import org.springframework.web.filter.CharacterEncodingFilter;


public class CharacterEncodingUTF8Filter extends CharacterEncodingFilter{
	
	public CharacterEncodingUTF8Filter() {
		// TODO Auto-generated constructor stub
		super("utf-8");
	}
	
	public static CharacterEncodingUTF8Filter getInstance = new CharacterEncodingUTF8Filter();

}
