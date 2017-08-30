package com.spittr.websocket.core;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;

import com.spittr.authorization.core.TokenIssues;
import com.spittr.authorization.exception.TokenErrorException;
import com.spittr.authorization.manager.TokenManager;
import com.spittr.authorization.model.Token;
import com.spittr.config.StaticConfig;
import com.spittr.user.model.User;
import com.spittr.websocket.exception.RequiredAuthorizationException;
import com.spittr.websocket.exception.StompHeaderRequiredException;
import com.spittr.websocket.model.WebSocketConstant;


public class SocketAuthorization {

	
	
	@SuppressWarnings("unchecked")
	public static User getUser(Message<Object> message) throws RuntimeException{
		Map<String, Object> sessionAttribute = (Map<String, Object>)message.getHeaders().get(StompHeaderAccessor.SESSION_ATTRIBUTES);
		User user = (User)sessionAttribute.get(WebSocketConstant.CURRENT_USER);
		if (user == null) 
			throw new RequiredAuthorizationException();
		
		return user;
	}
	
	
}
