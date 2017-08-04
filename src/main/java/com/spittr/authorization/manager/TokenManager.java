package com.spittr.authorization.manager;

import com.spittr.authorization.model.Token;
import com.spittr.user.model.User;

public interface TokenManager {

	public static final Long TOKEN_LIFETIME = 3600000L;
	
	Token createToken(User user);
	
	Boolean checkToken(Token token);
	
	void updateToken(Token token);
	
	Token getToken(String secret);
	
	void deleteToken(Token token);
	
	void deleteTokens(Long uid);
}
