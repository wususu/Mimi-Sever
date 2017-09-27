package com.spittr.authorization.core;

import java.util.Date;
import java.util.UUID;

import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.authorization.exception.TokenExpiredException;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;
import com.spittr.user.model.User;


public class TokenIssues {
	
	public static TokenIssues getInstance(){
		return Inner.instance;
	}
	
	private static class Inner{
		public static TokenIssues instance = new TokenIssues();
	}
	
	private TokenIssues(){
		
	}
	
	public Token tokenGenerator(User user){
		Token token = Token.newInstance(newSecret(), user);
		token.setTmExpire(new Date(System.currentTimeMillis() + TokenManager.TOKEN_LIFETIME));
		return token;
	}
	
	public boolean checkToken(Token token) {
		// TODO Auto-generated method stub
		
		if (token == null) 
			throw new TokenErrorException();
		
		User user = token.getUser();
		
		if (user == null || user.getPlogin()  ==  false) 
			return false;
		
		if (token.getTmExpire().getTime() < System.currentTimeMillis()) 
			throw new TokenExpiredException(user.getUname());
				
		return true;
	}
	
	private String newSecret(){
		String secret = UUID.randomUUID().toString().replaceAll("-", "");
		return secret;
	}
}
