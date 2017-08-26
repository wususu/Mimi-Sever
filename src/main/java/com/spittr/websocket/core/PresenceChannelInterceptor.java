package com.spittr.websocket.core;

import java.util.HashSet;
import java.util.Set;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import com.spittr.user.model.User;

@Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter{

	public static final Set<User> session = new HashSet<>();
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
		if (stompHeaderAccessor.getCommand() == null) 
			return ;
		User user = (User)stompHeaderAccessor.getSessionAttributes().get("user");
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
		session.add(user);
	}
	
	private void disconnect(StompHeaderAccessor stompHeaderAccessor, User user){
		stompHeaderAccessor.getSessionAttributes().remove("user");
		stompHeaderAccessor.getSessionAttributes().remove("srect");
		
		if (session.contains(user)) 
			session.remove(user);
	
	}
	
	
}
