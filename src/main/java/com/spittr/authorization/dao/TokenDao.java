package com.spittr.authorization.dao;

import java.util.List;

import com.spittr.authorization.model.Token;
import com.spittr.dao.BaseDao;


public interface TokenDao extends BaseDao<Token>{
	
	Token get(String secret);
	
	List<Token> get(Long uid);
 	
}
