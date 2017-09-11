package com.spittr.websocket.core;

import java.util.HashSet;
import java.util.Set;

import com.spittr.user.model.User;


public class WSSession {
	
	private WSSession(){
		throw new AssertionError();
	}
	
	private static final Set<User> USER_SESSION;
	
	static{
		USER_SESSION = new HashSet<>();
	}
	
	public static synchronized void addUser(User user){
		USER_SESSION.add(user);
	}
	
	public static synchronized void removeUser(User user){
		USER_SESSION.remove(user);
	}
	
	public static synchronized boolean containsUSer(User user) {
		return USER_SESSION.contains(user);
	}
	
	public static Set<User> getAllUser(){
		return USER_SESSION;
	}
}
