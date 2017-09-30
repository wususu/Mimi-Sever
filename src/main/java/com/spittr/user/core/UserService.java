package com.spittr.user.core;

import com.spittr.authorization.model.Token;
import com.spittr.user.model.User;

public interface UserService {
	
	public static User newInstance(String uname){
		return newInstance(uname, null);
	}
	
	public static User newInstance(String uname, String nname){
		return  new User(uname, nname);
	}
	
	User create(String uname, String nname, String passwd);
	
	Token login(String uname, String passwd);
	
	User get(Long id);
	
	User get(String uname);
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);
	
	void logout(User user);
}
