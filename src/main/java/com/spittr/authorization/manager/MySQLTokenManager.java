package com.spittr.authorization.manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spittr.authorization.core.TokenIssues;
import com.spittr.authorization.dao.TokenDao;
import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.authorization.exception.TokenExpiredException;
import com.spittr.authorization.model.Token;
import com.spittr.user.model.User;


@Service
public class MySQLTokenManager implements TokenManager{

	@Autowired
	private TokenDao tokenDao;
	
	@Override
	public Token createToken(User user) {
		// TODO Auto-generated method stub		
		Token token = TokenIssues.getInstance().tokenGenerator(user);

		tokenDao.save(token);
		
		return token;
	}

	@Override
	@Deprecated
	public Boolean checkToken(Token token) {
		// TODO Auto-generated method stub
		
		if (token == null) 
			throw new TokenErrorException();
		
		User user = token.getUser();
		
		if (user == null || user.getPlogin()  ==  false) 
			throw new TokenErrorException();
		
		if (token.getTmExpire().getTime() < System.currentTimeMillis()) 
			throw new TokenExpiredException(user.getUname());
		
		
		token.setTmExpire(new Date(System.currentTimeMillis() + TokenManager.TOKEN_LIFETIME));
		tokenDao.update(token);
		
		return true;
	}
	
	@Override
	public void deleteTokens(Long uid){
		List<Token> tokenList = tokenDao.get(uid);
		for (Token token : tokenList) {
			deleteToken(token);
		}
	}
	
	@Override
	public void updateToken(Token token) {
		// TODO Auto-generated method stub
		token.setTmExpire(new Date(System.currentTimeMillis() + TokenManager.TOKEN_LIFETIME));
		tokenDao.update(token);		
	}

	@Override
	public Token getToken(String secret) {
		// TODO Auto-generated method stub
		Token token = tokenDao.get(secret);
		return token;
	}

	@Override
	public void deleteToken(Token token) {
		// TODO Auto-generated method stub
		tokenDao.delete(token);
	}

}
