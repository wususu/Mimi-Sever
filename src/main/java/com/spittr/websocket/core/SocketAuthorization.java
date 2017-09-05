package com.spittr.websocket.core;

import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import com.spittr.user.model.User;
import com.spittr.websocket.config.WebSocketConstant;
import com.spittr.websocket.exception.RequiredAuthorizationException;


public class SocketAuthorization {
	
	@SuppressWarnings("unchecked")
	public static User getUser(Message<Object> message) throws RuntimeException{
		Map<String, Object> sessionAttribute = (Map<String, Object>)message.getHeaders().get(StompHeaderAccessor.SESSION_ATTRIBUTES);
		User user = (User)sessionAttribute.get(WebSocketConstant.CURRENT_USER_OBJ);
		if (user == null) 
			throw new RequiredAuthorizationException();
		
		return user;
	}
	
	
}
