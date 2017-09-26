package com.spittr.message.core;

import java.util.Map;

import com.spittr.message.model.Likee;
import com.spittr.message.model.Message;
import com.spittr.user.model.User;

public interface LikeeService {

	static Likee newInstance(Message message, User user){
		return new Likee(message, user);
	}
	
	void create(Likee entity);
	
	Likee get(long mid, long uid);
	
	Likee get(Message message, User user);
	
	Map<String, Object>  likee(Likee like);
	
	void generateLikee(Message message, User user);
}
