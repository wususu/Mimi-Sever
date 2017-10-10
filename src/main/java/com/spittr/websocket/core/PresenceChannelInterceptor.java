package com.spittr.websocket.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import com.spittr.authorization.manager.TokenManager;
import com.spittr.user.model.User;
import com.spittr.websocket.config.WebSocketConstant;

@Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter{
	
	@Autowired
	@Qualifier("mySQLTokenManager")
	protected TokenManager tokenManager;
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
		if (stompHeaderAccessor.getCommand() == null) 
			return ;
		User user = (User)stompHeaderAccessor.getSessionAttributes().get(WebSocketConstant.CURRENT_USER_OBJ);
		
		switch (stompHeaderAccessor.getCommand()) {
		case CONNECT:
			connect(user);
			break;
		case CONNECTED:
			break;
		case DISCONNECT:
			disconnect(stompHeaderAccessor, user);
			break;
		default:
			
			break;
		}
	}
	
	private void connect(User user){
		WSSession.addUser(user);
	}
	
	private void disconnect(StompHeaderAccessor stompHeaderAccessor, User user){
		stompHeaderAccessor.getSessionAttributes().remove(WebSocketConstant.CURRENT_USER_OBJ);
		
		if (WSSession.containsUSer(user)) 
			WSSession.removeUser(user);
	
	}
	
	
}
