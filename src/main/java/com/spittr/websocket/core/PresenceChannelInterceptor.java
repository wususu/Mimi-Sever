package com.spittr.websocket.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.authentication.Sha256PasswordPlugin;
import com.spittr.user.model.User;

@Component
public class PresenceChannelInterceptor extends ChannelInterceptorAdapter{

	public static final List<User> session = new ArrayList<>();
	
	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		// TODO Auto-generated method stub
		System.out.println("1111");
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
		if (stompHeaderAccessor.getCommand() == null) 
			return ;
		User user = (User)stompHeaderAccessor.getSessionAttributes().get("user");
		System.out.println(stompHeaderAccessor.getCommand());
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
		System.out.println("STOMP connect [ user: " +user.getUname() + "]");
		session.add(user);
	}
	
	private void disconnect(StompHeaderAccessor stompHeaderAccessor, User user){
		System.out.println("STOMP disconnect [ user: " +user.getUname() + "]");
		stompHeaderAccessor.getSessionAttributes().remove("user");
		stompHeaderAccessor.getSessionAttributes().remove("srect");
		
		if (session.contains(user)) 
			session.remove(user);
	
	}
	
	
}
