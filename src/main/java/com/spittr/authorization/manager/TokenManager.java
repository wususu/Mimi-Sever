package com.spittr.authorization.manager;

import com.spittr.authorization.model.Token;
import com.spittr.config.StaticConfig;
import com.spittr.user.model.User;

public interface TokenManager {

	public static final Long TOKEN_LIFETIME = StaticConfig.Token_EXPIRED_TIME;
	
	Token createToken(User user);
	
	Boolean checkToken(Token token);
	
	void updateToken(Token token);
	
	Token getToken(String secret);
	
	void deleteToken(Token token);
	
	void deleteTokens(Long uid);
}
