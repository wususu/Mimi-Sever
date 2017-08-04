package com.spittr.user.core;

import com.spittr.authorization.model.Token;
import com.spittr.user.model.User;

public interface UserService {
	
	void create(String uname, String nname, String passwd);
	
	Token login(String uname, String passwd);
	
	User get(Long id);
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);
	
	void logout(User user);
}
